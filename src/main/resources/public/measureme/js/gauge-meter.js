/**
 * 
 */
/**
 * 
 */
'use strict';

google.charts.load('current', {'packages':['gauge']});

const gaugeMeter = function(){
	
	var gaugeOptions = {min: 0, max: 100, yellowFrom: 80, yellowTo: 90,
            redFrom: 90, redTo: 100, minorTicks: 5};

	var gauge = null;
	var gaugeData = null;
	
	function init() {
		if(gauge !== null) {
			gauge.clearChart();
		}
		google.charts.setOnLoadCallback(drawGauge);
	}
	
	function drawGauge() {
		gaugeData = new google.visualization.DataTable();
		gaugeData.addColumn('number', 'Min');
		gaugeData.addColumn('number', 'Avg');
		gaugeData.addColumn('number', 'Max');
		gaugeData.addRow();
		gaugeData.setCell(0,0,50);
		gaugeData.setCell(0,1,50);
		gaugeData.setCell(0,2,50);
		
		gauge = new google.visualization.Gauge(document.getElementById('gauge-chart'));
	    gauge.draw(gaugeData, gaugeOptions);
	}

	function updateGaugeOptions(threshold) {
		var max = threshold * 2;
		gaugeOptions = {
			min: 0,
			max: max,
			yellowFrom: max - max * .2,
			yellowTo: max - max * .1,
			redFrom: max - max * .1,
			redTo: max,
			minorTicks: max / 20
		};
		gauge.draw(gaugeData, gaugeOptions);
	}
	
	function resizeCharts(){
		gauge = new google.visualization.Gauge(document.getElementById('gauge-chart'));
	    gauge.draw(gaugeData, gaugeOptions);
	}

	function setMinValue(value){
		gaugeData.setValue(0, 0, value);
		gauge.draw(gaugeData, gaugeOptions);
	}
	function setAvgValue(value){
		gaugeData.setValue(0, 1, value);
		gauge.draw(gaugeData, gaugeOptions);
	}
	function setMaxValue(value) {
		gaugeData.setValue(0, 2, value);
		gauge.draw(gaugeData, gaugeOptions);
	}
	
	function updateValues(data) {
		gaugeMeter.setMinValue(data.delayMin);
		gaugeMeter.setMaxValue(data.delayMax);
		gaugeMeter.setAvgValue(data.delayAvg);
	}
	
	function resetGauges(){
		gaugeMeter.setMinValue(0);
		gaugeMeter.setMaxValue(0);
		gaugeMeter.setAvgValue(0);
	}
	
	return {
		init: init,
		setMinValue: setMinValue,
		setMaxValue: setMaxValue,
		setAvgValue: setAvgValue,
		updateOptions: updateGaugeOptions,
		resetGauges: resetGauges,
		updateValues: updateValues,
		resizeCharts: resizeCharts
	}
}();