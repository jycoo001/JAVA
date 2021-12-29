<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="description" content="Change OR Die" />

<div class="topl"></div>

<title>404-抱歉，你所请求的页面不存在</title>
	<style>
		html,body {
			width:100%;
			height:100%;
			margin:0;
			padding:0;
			overflow:hidden;
		}
		body {
			background-color:#000;
			background-size:100% 100%;
		}
		#show {
			display:none;
		}
		#clock {
			position:absolute;
			top:0;
			left:0;
			text-align:center;
			color:#fff;
			font-family:"等线 Light","Microsoft Yahei Light";
			font-size:200px;
			text-shadow: 0 0 20px yellow;
			z-index:10;
		}
		#clock .sec {
			font-size:0.5em;
		}
		#time {
			position:relative;
		}
		#clock .st {
			position:absolute;
			right:2%;
			top:15%;
			font-size:0.25em;
			line-height:1;
		}
		#can {
			margin:0;
			padding:0;
			position:absolute;
			left:0;
			top:0;
			z-index:20;
		}
	</style>
	
	<style type="text/css">
	*{margin:0;padding:0;list-style-type:none;}
	a,img{border:0;}
	body{font:12px/180% Arial, Helvetica, sans-serif, "新宋体";}
	body {
		padding:0;
		margin:0;
		overflow:hidden;

		background-image:url(https://api.ixiaowai.cn/mcapi/mcapi.php);
		background-position: center center;
		background-repeat: no-repeat;
		background-size: cover;
	}
	canvas {
		padding:0;
		margin:0;
		opacity:1;
	}
	#sakura{
		opacity:0.2;
	}
	div.btnbg {
		position:fixed;
		left:0;
		top:0;
	}
	</style>
</head>

<body>


<div id="show">jycQQ</div>
	<div id="clock">00:00</div>
	<canvas id="can">您的浏览器不支持canvas标签</canvas>
	<canvas id="sakura"></canvas>
	<script>
		window.requestAnimFrame = (function(){
			return  window.requestAnimationFrame       ||
					window.webkitRequestAnimationFrame ||
					window.mozRequestAnimationFrame    ||
					function( callback ){
						window.setTimeout(callback, 1000 / 60);
					};
		})();
		var w, h, minW;
		var show = document.querySelector("#show");
		function showi(str){
			show.innerHTML = str;
		}
		var oClock = document.querySelector("#clock");
		var tStyle = true;
		/* 时间 */
		function getTime(){
			var t = new Date();
			if(tStyle){
				oClock.innerHTML = add0(t.getHours())+" : "+add0(t.getMinutes())+" <span class='sec'>"+add0(t.getSeconds())+"</span>";
			}else{
				var h = t.getHours();
				var str = h<12 ? "AM" : "PM";
				//var str = h<12 ? "上午" : "下午";
				h = h<=12 ? h : h-12;
				oClock.innerHTML = "<span id='time'>"+add0(h)+" : "+add0(t.getMinutes())+" <span class='sec'>"+add0(t.getSeconds())+"</span><span class='st'>"+str+"</span></span>";
			}
		};
		function autoTime(){
			getTime();
			setTimeout(autoTime, 1000);
		}
		function add0(n){
			return n<10 ? '0'+n : ''+n;
		}
		autoTime();

		// 设定参数
		var param = {
			style : 1, // 样式
			r : 0.45, // 圆的半径
			color : "rgba(255,255,255,0.8)", // 颜色
			blurColor : "#ffcccc", // 模糊颜色
			arr1 : [], // 外圆点集合
			arr2 : [], // 内圆点集合
			rotation : 0, // 是否旋转
			offsetAngle : 0, //旋转角度
			arr : [],  // 获取到的频谱数组
			waveArr : new Array(120),
			cX : 0.5, // 圆中心点在x轴位置
			cY : 0.5,
			tX : 50, // 时间在x轴上的位置
			tY : 50,
			range : 9, // 振幅
			shadowBlur: 15,
			lineWidth : 9,
			showCircle : true,
			wavetransparency : 0.8,
			timetransparency : 0.8,
			sakuratransparency : 0.8,
			showSemiCircle : false,
			SemiCircledirection : 1
		};

		var can = document.querySelector("#can");
		var ctx = can.getContext("2d");
		
		var sakura = document.getElementById("sakura");
        var sakura_obj = sakura.getContext('experimental-webgl');
		
		function resize(){
			can.width = w = window.innerWidth;
			can.height = h = window.innerHeight;
			minW = Math.min(w, h);
			oClock.style.width = w+'px';
			oClock.style.height = oClock.style.lineHeight = h+'px';
			setCan();
		};
		resize();
		oClock.style.fontSize = Math.floor(h/300*20) + 'px';
		window.onresize = resize;

		function setCan(){
			ctx.strokeStyle = param.color;
			ctx.lineWidth = param.lineWidth;
			ctx.shadowBlur = param.shadowBlur;
			ctx.shadowColor = param.blurColor;
		};

		/* 监听配置 */
		window.wallpaperPropertyListener={
            applyUserProperties: function(properties){
				//目录播放
				if (properties.customrandomdirectory) {
					if( properties.customrandomdirectory.value ) { 
						// directory set
					}
					else {
						// no directory set
					}
				}else{
					// 非目录，单一背景图
					if(properties.image){
						if(properties.image.value){
							document.body.style.backgroundImage = "url('file:///"+ properties.image.value +"')";
						}else{
							document.body.style.backgroundImage = "url('1.jpg')";
						}
						document.body.style.backgroundSize = '100% 100%';
					};
				};
				
				// 样式
				if(properties.style){
					param.style = properties.style.value;
				};
				// 半径
				if(properties.radius){
					param.r = properties.radius.value/100;
				};
				// 幅度
				if(properties.range){
					param.range = properties.range.value/5;
				};
				// 颜色
				if(properties.color){
					var c = properties.color.value.split(' ').map(function(c){return Math.ceil(c*255)});
					ctx.strokeStyle = param.color = 'rgba('+ c +',0.8)';
					oClock.style.color = 'rgb('+c+')';
				};
				// 模糊颜色
				if(properties.blurColor){
					var c = properties.blurColor.value.split(' ').map(function(c){return Math.ceil(c*255)});
					ctx.shadowColor = param.blurColor = 'rgb('+ c +')';
					oClock.style.textShadow = '0 0 20px rgb('+c+')';
				};
				// 是否显示时间
				if(properties.showTime){
					oClock.style.display = properties.showTime.value ? 'block' : 'none';
				};
				// 圆的位置
				if(properties.cX){
					param.cX = properties.cX.value*0.01;
				};
				if(properties.cY){
					param.cY = properties.cY.value*0.01;
				};
				// 时间的位置
				if(properties.tX){
					param.tX = properties.tX.value;
					oClock.style.left = param.tX-50+'%';
				};
				if(properties.tY){
					param.tY = properties.tY.value;
					oClock.style.top = param.tY-50+'%';
				};
				// 时间大小
				if(properties.tSize){
					var s = properties.tSize.value;
					oClock.style.fontSize = Math.floor(h/300*s) + 'px';
				};
				// 时间制式
				if(properties.tStyle){
					tStyle = properties.tStyle.value;
					getTime();
				};
				// 是否旋转
				if(properties.rotation){
					param.rotation = properties.rotation.value;
				};
				// 线宽
				if(properties.lineWidth){
					ctx.lineWidth = param.lineWidth = properties.lineWidth.value;
				};
				// 是否显示圆
				if(properties.showCircle){
					param.showCircle = properties.showCircle.value;
				};
				// 方向
				if(properties.direction){
					param.direction = properties.direction.value;
				};
				//樱花透明度
				if(properties.sakuratransparency){
					param.sakuratransparency = properties.sakuratransparency.value/100;
					sakura_obj.canvas.style.opacity = param.sakuratransparency
				};
				//可视化音频透明度
				if(properties.wavetransparency){
					param.wavetransparency = properties.wavetransparency.value/100;
					ctx.globalAlpha = param.wavetransparency;
				};
				//时间透明度
				if(properties.timetransparency){
					param.timetransparency = properties.timetransparency.value/100;
					oClock.style.opacity = param.timetransparency;
				};
				//显示为半圆
				if(properties.showSemiCircle){
					param.showSemiCircle = properties.showSemiCircle.value;
				};
				//显示为半圆
				if(properties.SemiCircledirection){
					param.SemiCircledirection = properties.SemiCircledirection.value;
				};
			}
		}

		/* 生成点 */
		function createPoint(arr){
			param.arr1 = [];
			param.arr2 = [];
			for(var i=0; i<120; i++){
				var deg 
				if(param.showSemiCircle){
					switch (param.SemiCircledirection) {
						case 1://上
							deg = Math.PI/120*(i+param.offsetAngle)*-1; //半圆角度
						break;
						case 2://下
							deg = Math.PI/120*(i+param.offsetAngle); //半圆角度
						break;
						/*
						case 3://左
							deg = Math.PI/180*(i+param.offsetAngle)*-0.7//半圆角度
						break;
						case 4://右
							deg = Math.PI/120*(i+param.offsetAngle)*0.5; //半圆角度
						break;
						*/
					}
				}else{
					deg = Math.PI/180*(i+param.offsetAngle)*3; //全圆角度
				};
				var w1 = arr[i] ? arr[i] : 0;
				var w2;
				if(param.waveArr[i]){
					w2 = param.waveArr[i] - 0.1;
				}else{
					w2 = 0;
				};
				w1 = Math.max(w1, w2);
				param.waveArr[i] = w1 = Math.min(w1, 1.2);
				var w = w1*param.range*100;
				var offset1
				var offset2
				switch (param.direction) {
					case 1:
						offset1 = param.r*minW/2+w+1;
						offset2 = param.r*minW/2;
						break;
					case 2:
						offset1 = param.r*minW/2; // 外圆偏移
						offset2 = param.r*minW/2-w-1; // 内圆偏移
						break;
					case 3:
						offset1 = param.r*minW/2+w+1; // 外圆偏移
						offset2 = param.r*minW/2-w-1; // 内圆偏移
						break;
				}
				var p1 = getXY(offset1, deg);
				var p2 = getXY(offset2, deg);
				param.arr1.push({'x':p1.x, 'y':p1.y});
				param.arr2.push({'x':p2.x, 'y':p2.y});
			};
			if(param.rotation){
				param.offsetAngle += param.rotation/20;
				if(param.offsetAngle>=360){
					param.offsetAngle = 0;
				}else if(param.offsetAngle<=0){
					param.offsetAngle = 360;
				}
			};
		};
		function getXY(offset, deg){
			return {'x':Math.cos(deg)*offset+param.cX*w, 'y':Math.sin(deg)*offset+param.cY*h};
		};
		createPoint([]);
		/* 连线 */
		function style1(){
			// 内外圆连线
			ctx.beginPath();
			for(var i=0; i<120; i++){
				ctx.moveTo(param.arr1[i].x, param.arr1[i].y);
				ctx.lineTo(param.arr2[i].x, param.arr2[i].y);
			};
			ctx.closePath();
			ctx.stroke();
		};
		function style2(){
			// 外圆
			ctx.beginPath();
			ctx.moveTo(param.arr1[0].x, param.arr1[0].y);
			for(var i=0; i<120; i++){
			ctx.lineTo(param.arr1[i].x, param.arr1[i].y);
			};
			if(!param.showSemiCircle){
				ctx.closePath();
			};
			ctx.stroke();
			// 内圆
			ctx.beginPath();
			if(param.showSemiCircle){
				ctx.moveTo(param.arr2[0].x, param.arr2[0].y);
				for(var i=0; i<120; i++){
					ctx.lineTo(param.arr2[i].x, param.arr2[i].y);
				};
			}else{
				ctx.moveTo(param.arr2[0].x, param.arr2[0].y);
				for(var i=0; i<120; i++){
					ctx.lineTo(param.arr2[i].x, param.arr2[i].y);
				};
			};
			if(!param.showSemiCircle){
				ctx.closePath();
			};
			ctx.stroke();
			// 内外圆连线
			ctx.beginPath();
			for(var i=0; i<120; i++){
				ctx.moveTo(param.arr1[i].x, param.arr1[i].y);
				ctx.lineTo(param.arr2[i].x, param.arr2[i].y);
			};
			ctx.closePath();
			ctx.stroke();
			
		};
		function style3(){
			// 外圆
			ctx.beginPath();
			ctx.moveTo(param.arr1[0].x, param.arr1[0].y);
			for(var i=0; i<120; i++){
			ctx.lineTo(param.arr1[i].x, param.arr1[i].y);
			};
			if(!param.showSemiCircle){
				ctx.closePath();
			};
			ctx.stroke();
			// 内圆
			ctx.beginPath();
			if(param.showSemiCircle){
				ctx.moveTo(param.arr2[0].x, param.arr2[0].y);
				for(var i=0; i<120; i++){
					ctx.lineTo(param.arr2[i].x, param.arr2[i].y);
				};
			}else{
				ctx.moveTo(param.arr2[0].x, param.arr2[0].y);
				for(var i=0; i<120; i++){
					ctx.lineTo(param.arr2[i].x, param.arr2[i].y);
				};
			};
			if(!param.showSemiCircle){
				ctx.closePath();
			};
			ctx.stroke();
		};
		style1();
		window.wallpaperRegisterAudioListener && window.wallpaperRegisterAudioListener(wallpaperAudioListener);
		function wallpaperAudioListener(arr){
			ctx.clearRect(0,0,w,h);
			createPoint(arr);
			if( param.showCircle ){
				switch (param.style) {
					case 1:
					style1();
					break;
					case 2:
					style2();
					break;
					case 3:
					style3();
					break;
				}
			}
		}
		/*function auto(){
			ctx.clearRect(0,0,w,h);
			createPoint(param.arr);
			style1();
			requestAnimFrame(auto);
		}
		auto();*/
	</script>
<!-- sakura shader -->
<script id="sakura_point_vsh" type="x-shader/x_vertex">
//樱花
uniform mat4 uProjection;
uniform mat4 uModelview;
uniform vec3 uResolution;
uniform vec3 uOffset;
uniform vec3 uDOF;  //x:focus distance, y:focus radius, z:max radius
uniform vec3 uFade; //x:start distance, y:half distance, z:near fade start

attribute vec3 aPosition;
attribute vec3 aEuler;
attribute vec2 aMisc; //x:size, y:fade

varying vec3 pposition;
varying float psize;
varying float palpha;
varying float pdist;

//varying mat3 rotMat;
varying vec3 normX;
varying vec3 normY;
varying vec3 normZ;
varying vec3 normal;

varying float diffuse;
varying float specular;
varying float rstop;
varying float distancefade;

void main(void) {
    // Projection is based on vertical angle
    vec4 pos = uModelview * vec4(aPosition + uOffset, 1.0);
    gl_Position = uProjection * pos;
    gl_PointSize = aMisc.x * uProjection[1][1] / -pos.z * uResolution.y * 0.5;

    pposition = pos.xyz;
    psize = aMisc.x;
    pdist = length(pos.xyz);
    palpha = smoothstep(0.0, 1.0, (pdist - 0.1) / uFade.z);

    vec3 elrsn = sin(aEuler);
    vec3 elrcs = cos(aEuler);
    mat3 rotx = mat3(
        1.0, 0.0, 0.0,
        0.0, elrcs.x, elrsn.x,
        0.0, -elrsn.x, elrcs.x
    );
    mat3 roty = mat3(
        elrcs.y, 0.0, -elrsn.y,
        0.0, 1.0, 0.0,
        elrsn.y, 0.0, elrcs.y
    );
    mat3 rotz = mat3(
        elrcs.z, elrsn.z, 0.0, 
        -elrsn.z, elrcs.z, 0.0,
        0.0, 0.0, 1.0
    );
    mat3 rotmat = rotx * roty * rotz;
    normal = rotmat[2];

    mat3 trrotm = mat3(
        rotmat[0][0], rotmat[1][0], rotmat[2][0],
        rotmat[0][1], rotmat[1][1], rotmat[2][1],
        rotmat[0][2], rotmat[1][2], rotmat[2][2]
    );
    normX = trrotm[0];
    normY = trrotm[1];
    normZ = trrotm[2];

    const vec3 lit = vec3(0.6917144638660746, 0.6917144638660746, -0.20751433915982237);

    float tmpdfs = dot(lit, normal);
    if(tmpdfs < 0.0) {
        normal = -normal;
        tmpdfs = dot(lit, normal);
    }
    diffuse = 0.4 + tmpdfs;

    vec3 eyev = normalize(-pos.xyz);
    if(dot(eyev, normal) > 0.0) {
        vec3 hv = normalize(eyev + lit);
        specular = pow(max(dot(hv, normal), 0.0), 20.0);
    }
    else {
        specular = 0.0;
    }

    rstop = clamp((abs(pdist - uDOF.x) - uDOF.y) / uDOF.z, 0.0, 1.0);
    rstop = pow(rstop, 0.5);
    //-0.69315 = ln(0.5)
    distancefade = min(1.0, exp((uFade.x - pdist) * 0.69315 / uFade.y));
}
</script>
<script id="sakura_point_fsh" type="x-shader/x_fragment">
#ifdef GL_ES
//precision mediump float;
precision highp float;
#endif

uniform vec3 uDOF;  //x:focus distance, y:focus radius, z:max radius
uniform vec3 uFade; //x:start distance, y:half distance, z:near fade start

const vec3 fadeCol = vec3(0.08, 0.03, 0.06);

varying vec3 pposition;
varying float psize;
varying float palpha;
varying float pdist;

//varying mat3 rotMat;
varying vec3 normX;
varying vec3 normY;
varying vec3 normZ;
varying vec3 normal;

varying float diffuse;
varying float specular;
varying float rstop;
varying float distancefade;

float ellipse(vec2 p, vec2 o, vec2 r) {
    vec2 lp = (p - o) / r;
    return length(lp) - 1.0;
}

void main(void) {
    vec3 p = vec3(gl_PointCoord - vec2(0.5, 0.5), 0.0) * 2.0;
    vec3 d = vec3(0.0, 0.0, -1.0);
    float nd = normZ.z; //dot(-normZ, d);
    if(abs(nd) < 0.0001) discard;

    float np = dot(normZ, p);
    vec3 tp = p + d * np / nd;
    vec2 coord = vec2(dot(normX, tp), dot(normY, tp));

    //angle = 15 degree
    const float flwrsn = 0.28819045102521;
    const float flwrcs = 0.965925826289068;
    mat2 flwrm = mat2(flwrcs, -flwrsn, flwrsn, flwrcs);
    vec2 flwrp = vec2(abs(coord.x), coord.y) * flwrm;

    float r;
    if(flwrp.x < 0.0) {
        r = ellipse(flwrp, vec2(0.065, 0.024) * 0.5, vec2(0.36, 0.96) * 0.5);
    }
    else {
        r = ellipse(flwrp, vec2(0.065, 0.024) * 0.5, vec2(0.58, 0.96) * 0.5);
    }

    if(r > rstop) discard;
	//花瓣颜色
    vec3 col = mix(vec3(1.0, 0.9, 0.75), vec3(1.0, 0.9, 0.87), r);
    float grady = mix(0.0, 1.0, pow(coord.y * 0.5 + 0.5, 0.35));
    col *= vec3(3.0, grady, grady);
    col *= mix(0.9, 1.0, pow(abs(coord.x), 1.0));
    col = col * diffuse + specular;

    col = mix(fadeCol, col, distancefade);

    float alpha = (rstop > 0.001)? (0.5 - r / (rstop * 2.0)) : 1.0;
    alpha = smoothstep(0.0, 1.0, alpha) * palpha;

    gl_FragColor = vec4(col * 3.0, alpha);
}
</script>
<!-- effects -->
<script id="fx_common_vsh" type="x-shader/x_vertex">
uniform vec3 uResolution;
attribute vec2 aPosition;

varying vec2 texCoord;
varying vec2 screenCoord;

void main(void) {
    gl_Position = vec4(aPosition, 0.0, 1.0);
    texCoord = aPosition.xy * 0.5 + vec2(0.5, 0.5);
    screenCoord = aPosition.xy * vec2(uResolution.z, 1.0);
}
</script>
<script id="bg_fsh" type="x-shader/x_fragment">
#ifdef GL_ES
//precision mediump float;
precision highp float;
#endif

uniform vec2 uTimes;

varying vec2 texCoord;
varying vec2 screenCoord;

void main(void) {
    vec3 col;
    float c;
    vec2 tmpv = texCoord * vec2(0.8, 1.0) - vec2(0.95, 1.0);
    c = exp(-pow(length(tmpv) * 1.2, 2.0));
    col = mix(vec3(0.05, 0.0, 0.03), vec3(0.96,0.91, 1.0) * 1.5, c);
    gl_FragColor = vec4(col * 0.5, 1.0);
}
</script>
<script id="fx_brightbuf_fsh" type="x-shader/x_fragment">
#ifdef GL_ES
//precision mediump float;
precision highp float;
#endif
uniform sampler2D uSrc;
uniform vec2 uDelta;

varying vec2 texCoord;
varying vec2 screenCoord;

void main(void) {
    vec4 col = texture2D(uSrc, texCoord);
    gl_FragColor = vec4(col.rgb * 2.0 - vec3(0.5), 1.0);
}
</script>
<script id="fx_dirblur_r4_fsh" type="x-shader/x_fragment">
#ifdef GL_ES
//precision mediump float;
precision highp float;
#endif
uniform sampler2D uSrc;
uniform vec2 uDelta;
uniform vec4 uBlurDir; //dir(x, y), stride(z, w)

varying vec2 texCoord;
varying vec2 screenCoord;

void main(void) {
    vec4 col = texture2D(uSrc, texCoord);
    col = col + texture2D(uSrc, texCoord + uBlurDir.xy * uDelta);
    col = col + texture2D(uSrc, texCoord - uBlurDir.xy * uDelta);
    col = col + texture2D(uSrc, texCoord + (uBlurDir.xy + uBlurDir.zw) * uDelta);
    col = col + texture2D(uSrc, texCoord - (uBlurDir.xy + uBlurDir.zw) * uDelta);
    gl_FragColor = col / 7.5;
}
</script>
<!-- effect fragment shader template -->
<script id="fx_common_fsh" type="x-shader/x_fragment">
#ifdef GL_ES
//precision mediump float;
precision highp float;
#endif
uniform sampler2D uSrc;
uniform vec2 uDelta;

varying vec2 texCoord;
varying vec2 screenCoord;

void main(void) {
    gl_FragColor = texture2D(uSrc, texCoord);
}
</script>
<!-- post processing -->
<script id="pp_final_vsh" type="x-shader/x_vertex">
uniform vec3 uResolution;
attribute vec2 aPosition;
varying vec2 texCoord;
varying vec2 screenCoord;
void main(void) {
    gl_Position = vec4(aPosition, 0.0, 1.0);
    texCoord = aPosition.xy * 0.5 + vec2(0.5, 0.5);
    screenCoord = aPosition.xy * vec2(uResolution.z, 1.0);
}
</script>
<script id="pp_final_fsh" type="x-shader/x_fragment">
#ifdef GL_ES
//precision mediump float;
precision highp float;
#endif
uniform sampler2D uSrc;
uniform sampler2D uBloom;
uniform vec2 uDelta;
varying vec2 texCoord;
varying vec2 screenCoord;
void main(void) {
    vec4 srccol = texture2D(uSrc, texCoord) * 2.0;
    vec4 bloomcol = texture2D(uBloom, texCoord);
    vec4 col;
    col = srccol + bloomcol * (vec4(1.0) + srccol);
    col *= smoothstep(1.0, 0.0, pow(length((texCoord - vec2(0.5)) * 2.0), 1.2) * 0.5);
    col = pow(col, vec4(0.45454545454545)); //(1.0 / 2.2)

    gl_FragColor = vec4(col.rgb, 1.0);
    gl_FragColor.a = 1.0;
}
</script>
  <script src="js/index.js"></script>
<div style="text-align:center;">
</div>
</body>
</html>
<br></br>