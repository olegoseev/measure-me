

window.addEventListener('DOMContentLoaded',(event)=> {
	
	const healthStatus = document.querySelector('#healthStatus');
	const dskSpaceTotal = document.querySelector('#dskTotal');
	const dskSpaceFree = document.querySelector('#dskFree');
	const dskSpaceThreshold = document.querySelector('#dskThreshold');
	const dbName = document.querySelector('#dbName');
	const dbStatus = document.querySelector('#dbStatus');
	
	const reqCnt = document.querySelector('#reqCnt');
	const reqTotalTime = document.querySelector('#reqTotalTime');
	const reqMaxTime = document.querySelector('#reqMaxTime');
	
	const homeVisits = document.querySelector('#homeCnt');
	const homeTotalTime = document.querySelector('#homeTotalTime');
	const homeMaxTime = document.querySelector('#homeMaxTime');
	
	const jvmUpTime = document.querySelector('#jvmUp');
	const jvmCpuUsage = document.querySelector('#jvmCpu');
	
	const jvmMemUsed = document.querySelector('#jvmMemoryUsed');
	const jvmMemory = document.querySelector('#jvmMemory');
	const jvmThrdLive = document.querySelector('#thrdLive');
	const jvmThrdPeak = document.querySelector('#thrdPeak');
	const jvmThrdDaemon = document.querySelector('#thrdDaemon');
	
	homeVisits.textContent = 'N/A';
	homeTotalTime.textContent = 'N/A';
	homeMaxTime.textContent = 'N/A';

	const requiredParam = (argName) => {
		throw new Error('$ is required');
	}
	
	const getDataFor = (url, responseHandler, element = requiredParam('element'), index = 0, roundOff = '') => {
		roundOff = roundOff === '' ? 0 : roundOff;
		fetch(url)
			.then(res => res.json())
			.then(data => responseHandler(data, element, index, roundOff))
			.catch((error) => {
			console.log('Error: ', error);
		});
	}
	
	
	const getData = (url, responseHandler) => {
		getDataFor(url, responseHandler, '');
	}
	
	const healthStatusInfo = (data) => {
		console.log(data)
        let ds = data.components.diskSpace.details;
        let dbs = data.components.db;
    	healthStatus.textContent = data.status;
    	dskSpaceTotal.textContent = ds.total;
    	dskSpaceFree.textContent = ds.free;
    	dskSpaceThreshold.textContent = ds.threshold;
    	dbName.textContent = dbs.details.database;
    	dbStatus.textContent = dbs.status;
	}
	

	const requestInfo = (data) => {
		reqCnt.textContent = data.measurements[0].value;
		reqTotalTime.textContent = data.measurements[1].value.toFixed(3);
		reqMaxTime.textContent = data.measurements[2].value.toFixed(3);
	}
	
	const homePageInfo = (data) => {
		homeVisits.textContent = data.measurements[0].value;
		homeTotalTime.textContent = data.measurements[1].value.toFixed(3);
		homeMaxTime.textContent = data.measurements[2].value.toFixed(3);
	}

	const appInfo = (data, element, index, roundOff) => {
		element.textContent = data.measurements[index].value.toFixed(roundOff);
	}
	
	getData('/actuator/health', healthStatusInfo);
	getData('/actuator/metrics/http.server.requests', requestInfo);
	getData('/actuator/metrics/api.getHomePage', homePageInfo);
	
//	getDataFor('/actuator/metrics/http.server.requests', appInfo, reqCnt, 0);
//	getDataFor('/actuator/metrics/http.server.requests', appInfo, reqTotalTime, 1, 5);
//	getDataFor('/actuator/metrics/http.server.requests', appInfo, reqMaxTime, 2, 5);
	
	getDataFor('/actuator/metrics/process.uptime', appInfo, jvmUpTime);
	getDataFor('/actuator/metrics/process.cpu.usage', appInfo, jvmCpuUsage, 0, 5);
	getDataFor('/actuator/metrics/jvm.memory.max', appInfo, jvmMemory);
	getDataFor('/actuator/metrics/jvm.memory.used', appInfo, jvmMemUsed);
	getDataFor('/actuator/metrics/jvm.threads.live', appInfo, jvmThrdLive);
	getDataFor('/actuator/metrics/jvm.threads.peak', appInfo, jvmThrdPeak);
	getDataFor('/actuator/metrics/jvm.threads.daemon', appInfo, jvmThrdDaemon);
	
});

