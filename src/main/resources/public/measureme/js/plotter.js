'use strict';

(function (global) {

    var Plotter = global.Plotter || (global.Plotter = {});

    Plotter.plot = {
        label: "",
        chart: null,
        ticks: 25,
        setThreshold: function (data) {
            this.chartData.datasets[0].data = [];
            for (var i = 0; i < this.ticks; i++) {
                this.chartData.datasets[0].data.push(data)
            }
            this.chart.update();
        },
        addData: function (data) {
            this.chartData.datasets[1].data.shift();
            this.chartData.datasets[1].data.push(data.below);
            this.chartData.datasets[2].data.shift();
            this.chartData.datasets[2].data.push(data.over);
            this.chart.update();
        },
        chartData: {
            labels: ['', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', ''],
            datasets: [
                {
                    type: 'line',
                    label: 'Threshold',
                    borderDash: [12, 4],
                    borderColor: 'rgb(88,111,119)',
                    backgroundColor: 'rgb(88,111,119)',
                    borderWidth: 2,
                    pointRadius: 0,
                    fill: false,
                    data: []
                },
                {
                    type: 'bar',
                    label: 'Below',
                    borderColor: 'rgba(69,104,211, 1)',
                    borderWidth: 1,
                    backgroundColor: 'rgba(69,104,211, .5)',
                    hoverBorderWidth: 2,
                    data: []
                },
                {
                    type: 'bar',
                    label: 'Over',
                    borderColor: 'rgba(205,54,209, 1)',
                    borderWidth: 1,
                    backgroundColor: 'rgba(205,54,209, .5)',
                    hoverBorderWidth: 2,
                    data: []
                }
            ]
        },
        init: function (elementId, label) {
            this.label = label;
            this.flushData();
            var data = this.chartData;
            var options = {
                responsive: true,
                maintainAspectRatio: false,
                title: {
                    display: false,
                    text: this.label//'Round trip time'
                },
                tooltips: {
                    mode: 'index',
                    intersect: true
                },
                legend: {
                	display: true,
                    labels: {
                    	boxWidth: 20,
                    	fontSize: 10,
                    },
                },

                scales: {
                    xAxes: [{
                    	stacked: true,
                    	gridLines: {
                    		display: false
                    	}
                    }],
                    yAxes: [{
                        stacked: true,
                        gridLines: {
                        	display: true,
                        	color: "rgba(128, 128, 128, 0.5)"
                        },
                        ticks: {
                            beginAtZero: true
                        }
                    }]
                }
            };
            this.chart = Chart.Bar(elementId, { options: options, data: data });
        },
        flushData: function () {
            for (var i = 0; i < this.ticks; i++) {
                this.chartData.datasets[1].data.push(0);
                this.chartData.datasets[2].data.push(0);
            }
        },
        clearChart: function () {
            this.chartData.datasets[1].data = [];
            this.chartData.datasets[2].data = [];
            this.flushData();
            this.chart.update();
        },
        displayLegend: function(height) {
        	if(height < 300) {
        		this.chart.options.legend.display = false;	
        	}else {
        		this.chart.options.legend.display = true;
        	}
        	this.chart.update();
        }
    }

}(this));
