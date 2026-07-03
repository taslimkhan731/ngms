 // gauge1 uses the default liquid gauge setting
 //var gauge1 = loadLiquidFillGauge("fillgauge1", 55);
 
 // gauge2 uses the custom gauge setting
var config1 = liquidFillGaugeDefaultSettings(100);
 config1.circleColor = "#178BCA";
 config1.textColor = "#222224";
 config1.waveTextColor = "#222224";
 config1.waveColor = "#d64d4d";
 config1.circleThickness = 0.05;
 config1.textVertPosition = 0.2;
 config1.waveAnimateTime = 1000;
 //var gauge2 = loadLiquidFillGauge("fillgauge2", 28, config1);
 
 // gauge3 uses the custom gauge setting
function liquidfillgaugeDraw(id,time, fix,maxValue){
	 var config2 = liquidFillGaugeDefaultSettings(maxValue);
	 if (time<30) {
		 config2.waveColor = "#ff0000";
		 config2.waveTextColor = "#000000";
	} else if (time>30&&time<=50) {
		config2.waveColor = "#ff9933";
	}else if (time>50&&time<=80) {
		config2.waveColor = "#00ff00";
	}else{
		config2.waveColor = "#008000";
	}
	 config2.waveRise = true;
	 config2.waveHeightScaling= true;
	 config2.circleColor = "#178BCA";
	 config2.textColor = "#222224";
	 config2.waveTextColor = "#222224";
	 config1.circleThickness = 0.05;
	 config2.textVertPosition = 0.2;
	 config2.waveAnimateTime = 1000;
	 var gauge3 = loadLiquidFillGauge(id, time, config2,fix);
}

function liquidfillgaugeDrawAvgTime(id,time, fix,maxValue){
	 var config2 = liquidFillGaugeDefaultSettings(maxValue);
	 if (time<18) {
		 config2.waveColor = "#008000";
	} else if (time>18&&time<=30) {
		config2.waveColor = "#00ff00";
	}else if (time>30&&time<=48) {
		config2.waveColor = "#ff9933";
	}else{
		config2.waveColor = "#ff0000";
		config2.waveTextColor = "#000000";
	}
	 config2.waveRise = true;
	 config2.waveHeightScaling= true;
	 config2.circleColor = "#178BCA";
	 config2.textColor = "#222224";
	 config2.waveTextColor = "#222224";
	 config1.circleThickness = 0.05;
	 config2.textVertPosition = 0.2;
	 config2.waveAnimateTime = 1000;
	 var gauge3 = loadLiquidFillGauge(id, time, config2,fix);
}

 // gauge onclick responds to random fill percentages.
 function NewValue() {
   if (Math.random() > .5) {
     return Math.round(Math.random() * 100);
   } else {
     return (Math.random() * 100).toFixed(1);
   }
 }