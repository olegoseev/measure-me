'use strict';

(function(global){

    var Monitor = global.Monitor || (global.Monitor = {});

    Monitor.process = {
    	ping: function (url, callback) {
    		
    		let startTime = (new Date()).getTime();
    		fetch(url, {mode: 'no-cors', cache: 'no-cache'})
	    	.then(res => callback(startTime))
	    	.catch((error) =>{
	    		console.log('Error: ', error);
	    	});
    	},
        timerId: null,
        observer: null,
        start: function(url, interval){
            this.timerId = setInterval(this.ping, interval*1000, url, this.callback);
        },
        callback: function(data) {
            let measure = {
                    startTime: data,
                    stopTime: (new Date()).getTime()
                };

                Monitor.process.notify(measure);
        },
        stop: function(){
            clearInterval(this.timerId)
        },
        subscribe: function(observer){
            this.observer = observer;
        },
        notify: function(data) {
            if(this.observer != null) {
                this.observer(data);
            }
        }  
    }
}(this));