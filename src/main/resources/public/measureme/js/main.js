'use strict';

window.process = Monitor.process;
window.plot = Plotter.plot;

// ///////////////////////
// holds current session info
window.session = {
    sessionId: 0,
    siteName: '',
    reqTime: '',
    resTime: '',
    webApp: browserName,
    webAppVer: fullVersion
};

window.addEventListener('DOMContentLoaded',(event)=> {
	mainCtrl.startBtn.addEventListener('click', mainCtrl.monitor);
	mainCtrl.websiteUrl.addEventListener('input', mainCtrl.urlChange);
	plot.init('bar-chart', '');
	mainCtrl.displayChartLegend();
	gaugeMeter.init();
	window.onresize = mainCtrl.onResize;
});

const mainCtrl = function(){
	
	const configUrl = '/api/cfg/{name}';
	const resultsUrl = '/api/results';
	const summaryUrl = '/api/summary/{id}';
	const deleteUrl = '/api/results/{id}';

	
	const startBtn = document.querySelector('#start');
	const siteSelector = document.querySelector('#websites');
	const appInfoLink = document.querySelector('#appinfo');
	const inputFields = document.querySelectorAll('.input-field');
	const checkInterval = document.querySelector('#interval');
	const websiteUrl = document.querySelector('#url');
	const resposeThreshold = document.querySelector('#threshold');
	const btnStopColor = '#be1515';
	const btnStartColor = '#cf561a';

// /////////////////////////////////////////////////////////////
	// handles select field
	async function siteSelected() {
	    let siteName = siteSelector.value;

	    if (siteName !== session.siteName) {
	        session.siteName = siteName;
	        session.sessionId = 0;
	        plot.clearChart();
	    }
	    
	    let url = configUrl.replace('{name}',siteName);
	    let response = await fetch(url);
	    if(response.ok){
			let data = await response.json();
			websiteUrl.value = data.url;
			checkInterval.value = data.interval;
			resposeThreshold.value = data.threshold;
	    }else {
	    	console.log("siteSelected: error to get website configuration");
	    }
	}

	function monitor() {
	    let value = startBtn.value;
	    if (value === 'start') {
	        disableInputFields();
	        startMonitor()
	    } else if (value === 'stop') {
	        enableInputFileds();
	        stopMonitor();
		    cleanUp(session);
	    }
	}

	// ///////////////////////////////////////////////////////////
	// start monitoring process
	function startMonitor() {
	    let interval = checkInterval.value;
	    let url = urlFix(websiteUrl.value);
	    gaugeMeter.updateOptions(resposeThreshold.value);
	    gaugeMeter.resetGauges();
	    let threshold = resposeThreshold.value;
	    plot.clearChart();
	    plot.setThreshold(threshold);
	    
	    process.subscribe(receiver);
	    process.start(url, interval);
	}
	
	function urlFix(url){
	    let httpPos = url.indexOf("https://");
	    return httpPos === -1 ?  "https://" + url : url;
	}

	// /////////////////////////////////////////////////////////////
	// stop monitoring process
	function stopMonitor() {
	    process.stop();
	}

	// /////////////////////////////////////////////////////////////
	// called when response received from the testes website
	function receiver(data) {
	    let t1 = data.startTime;
	    let t2 = data.stopTime;
	    let roundTrip = t2 - t1;
	    // console.log("start time: " + data.startTime + " stop time: " +
		// data.stopTime + " delay: " + delay);

	    let threshold = resposeThreshold.value;
	    let overThreshold = roundTrip - threshold;
	    let chartData = {
	        below: overThreshold > 0 ? roundTrip - overThreshold : roundTrip,
	        over: overThreshold > 0 ? overThreshold : 0
	    }
	    plot.addData(chartData);
	    session.reqTime = t1;
	    session.resTime = t2;
	    createOrUpdateSession(session);
	}
	
	async function updateData(url = '', data = {}, method = 'POST') {
		const response = await fetch(url,
				{
					method: method,
					headers: {'Content-Type' : 'application/json'},
					mode: 'same-origin',
					body: JSON.stringify(data)
				});
		
		return await response.json();
	}

	function cleanUp(session) {
		let url = deleteUrl.replace('{id}', session.sessionId)
		updateData(url, {}, 'DELETE')
			.then((response) => {
				console.log("session clean up complete");
			})
			.catch((error) => {
				console.log('cleanUp Error: ', error);
			});
	}

	// ///////////////////////////////////////////////////////
	// sends session and test results (if any) to the server
	function createOrUpdateSession(session) {

		let data = {
				websiteName: session.siteName,
				sessionId: session.sessionId,
				requestTime: session.reqTime,
				responseTime: session.resTime,
				webAppName: session.webApp,
				webAppVer: session.webAppVer
		}
		
		updateData(resultsUrl, data)
			.then((response) => {
				session.sessionId = response.sessionId;
				updateSummaryGauge(session);
			})
			.catch((error) => {
				console.log('createOrUpdateSession Error: ', error);
			});
	}

	function updateSummaryGauge(session) {
		
	    let url = summaryUrl.replace('{id}',session.sessionId);
	    
	    fetch(url)
	    	.then(res => res.json())
	    	.then(data => gaugeMeter.updateValues(data))
	    	.catch((error) =>{
	    		console.log('Error: ', error);
	    	});
	}
	
	function siteUrlChange(){
		siteSelector.selectedIndex = 0;
	}

	// ///////////////////////////////////////////////////////////////
	// changing text, background color of the button
	function startBtnUpdate(value, content, color) {
	    startBtn.value = value;
	    startBtn.innerHTML = content;
	    startBtn.style.background = color;
	}

	// /////////////////////////////////////////////////////////////
	// enable input fields and button
	function enableInputFileds() {
	    startBtnUpdate('start', 'Start', btnStartColor);
	    inputFields.forEach(field => field.disabled = false);
	    siteSelector.disabled = false;
	    appInfoLink.classList.remove('disable-a');
	}

	// /////////////////////////////////////////////////////////////
	// disable input fields and button
	function disableInputFields() {
	    startBtnUpdate('stop', 'Stop', btnStopColor);
	    inputFields.forEach(field => field.disabled = true);
	    siteSelector.disabled = true;
	    appInfoLink.classList.add('disable-a');
	}
	
	function onResize() {
		gaugeMeter.resizeCharts;
		displayChartLegend();
	}
	
	function displayChartLegend() {
		let height = document.querySelector('#bar-chart').height;
		plot.displayLegend(height);
	}
	
	return {
		monitor: monitor,
		selected: siteSelected,
		urlChange: siteUrlChange,
		startBtn: startBtn,
		websiteUrl: websiteUrl,
		onResize: onResize,
		displayChartLegend: displayChartLegend
	}
}();