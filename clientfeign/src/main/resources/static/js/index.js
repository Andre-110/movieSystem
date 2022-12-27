// 创建图片对象，保存三个位置的图片
let selected = new Image();
let unselected = new Image();
let bought = new Image();
selected.src = "../../images/selected.png";
unselected.src = "../../images/unselected1.png";
bought.src = "../../images/bought.png";
//画布，通过画布来画所有的空白位置 
let myCanvas = document.getElementById("myCanvas");
// 设置画布的大小
myCanvas.width = 742;
myCanvas.height = 530;
// 创建一个2d的画笔
let ctx = myCanvas.getContext("2d");

//  设置一个存放用户临时选择的座位信
let selectSeat = new Array(0);

// 创建绘制座位的函数
function drawSeat(x, y, isSole, isSelect) {
	// 设置一下座位x和y的坐标,这里数据写反了，所以反着使用x和y
	let seatX = (y - 1) * 53;
	let seatY = (x - 1) * 53;
	if (isSelect=="1" && isSole=="0") {
		ctx.drawImage(selected, seatX, seatY, 48, 48);
	} else if(isSole=="1") {
		ctx.drawImage(bought, seatX, seatY, 48, 48);
	} else {
		ctx.drawImage(unselected, seatX, seatY, 48, 48);
	}
}

// 给座位区域添加一个点击事件
myCanvas.addEventListener("click", myCanvasBindEvent);

// 座位绑定事件的函数
function myCanvasBindEvent(event) {
	let price = 36.6;
	let x = event.offsetX;
	let y = event.offsetY;
	//判断点击的位置是48的多少倍,使用floor向下取整,来确定点击的是哪一个座位
	// i指的是列，j指的是行
	let i = Math.floor(x / 53) + 1;
	let j = Math.floor(y / 53) + 1;
	let index = (j - 1) * 14 + i;
	if(seatArry[index - 1].isSelect=="1"){
		seatArry[index - 1].isSelect = "0"
	}else{
		seatArry[index - 1].isSelect = "1"
	}
	$.each(seatArry, function(index, value) {
		// 遍历所以的座位对象，绘制用户点击以后的座位示意图
		drawSeat(value.x, value.y, value.isSole, value.isSelect);
	});
	// 如果用户点击的座位并没有被购买，那么就出现下面的提示，提示座位的坐标和价格
	if (seatArry[index - 1].isSole == "0") {
		Seathint(i, j, price);
	}
}

// 当用户点击了座位以后，会提示用户选中的座位和票价
function Seathint(row, col, price) {
	let index = (col - 1) * 14 + row;
	if (seatArry[index - 1].isSelect =="1") {
		selectSeat.push(seatArry[index - 1]);
	} else {
		let number = selectSeat.indexOf(seatArry[index - 1]);
		selectSeat.splice(number, 1);
	}
	seatChangHint(selectSeat, price);
}

// 座位示意图修改提示框
function seatChangHint(arry, price) {
	let count = 0;
	$("#hint").children(".hint").remove();
	$.each(selectSeat, function(index, value) {
		let $item = creatHintSeat(value.x, value.y, price);
		$("#hint").append($item);
		count++;
	});
	let sum = count*price;
	sum = sum.toFixed(2);
	let str = "￥"+sum +" "+"确认选座"
	$("#submit").find("p").text(str)
}


// 创建座位提示的位置和价格
function creatHintSeat(row, col, price) {
	let Coordinate = row + "排" + col + "座";
	let Price = "￥" + price;
	let $item = $(
		`<div class="hint">
			<p id="info">${Coordinate}</p>
			<p id="price">${Price}</p>
		</div>`
	)
	return $item;
}

// 提取用户选中的座位号的x和y
function distil() {
	// 设置一个存放用户选的座位的Id的数组
	let seatId = [];
	$.each(seatArry, function(index, value) {
		if (value.isSelect=="1") {
			seatId.push(value.data);
		}
	});
	return seatId;
}
// 初始化函数
function init() {
	$.each(seatArry, function(index, value) {
		// 遍历所以的座位对象，绘制最初的座位
		drawSeat(value.x, value.y, value.isSole, value.isSelect);
	});
}
init();


// 给按钮添加事件
$("#submit").bind("click",function(){
		let arr = distil();
		console.log(arr);
})