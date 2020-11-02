<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	<title>学生信息表</title>

</head>
<body class="container">
	<div style="background-color: whitesmoke">
		<a href="#" id="registerId">注册</a>
	</div>

	<table border="1px" width="65%" align="center"
		   id="tableId" class="table-bordered table-hover table-condensed">
		<thead>
		<tr>
			<td colspan="6" align="center"><h3>学生信息</h3></td>
		</tr>
		<tr>
			<td colSpan="4">
				<input class="form-control" id="find" placeholder="请输入查询条件">
			</td>
			<td align="center" colspan="2">
				<button type="button" style="box-shadow: none"
						class="btn btn-default btn-lg btn-block"
						id="btn">查询
				</button>
			</td>
		</tr>
		<tr align="center">
			<th>编号</th>
			<th>姓名</th>
			<th>年龄</th>
			<th>性别</th>
			<th colspan="2">操作</th>
		</tr>
		</thead>
		<tbody id="tbody">
			<tr><td colspan="4">loading.....</td></tr>
		</tbody>

	</table>
</body>
<script src="../js/jquery.min.js"></script>
<link href="../bootstrap-4.5.0/css/bootstrap.css" rel="stylesheet"/>
<script>
	//注册事件
	$(function(){
		$.get("/findAjax",getPageObjects);
		$("#find").on("keypress",doKeypress);
		$("#btn").on("click",findObjectByInput);
		$("#tbody").on("click",".update",doUpdateById)
					.on("click",".delete",doDeleteById);
		$("#registerId").on("click",doRegister);
	});


	//查询事件
	function findObjectByInput() {
		let inp=$("#tableId input:first").val();
		console.log(inp);
		let url="/doFindObjectByConditions";
		let params={"condition":inp}
		$.post(url,params,getPageObjects);
	}

	//显示页面对象
	function getPageObjects(res) {
		//console.log(res);
		let teble=$("#tbody");
		teble.empty();
		if (res.length==0){
			let tr=`<tr><td colspan="4" >找不到指定对象...</td></tr>`;
			teble.append(tr);
		}
		res.forEach(function (user) {
			//console.log(user);
			let tr=$("<tr align='center'></tr>");
			let td=`<td>\${user.id}</td>
					<td>\${user.name}</td>
					<td>\${user.age}</td>
					<td>\${user.sex}</td>
					<td>
						<button style="box-shadow: none"
							class="btn btn-link update">
							修改
						</button>
					</td>
					<td>
<button
style="box-shadow: none"
class="btn btn-link delete" href="#myPopup1" data-rel="popup"
>删除</button>
					</td>`;
			tr.append(td);
			teble.append(tr);
			//在每一行绑定该用户数据
			tr.data("user",user);
		})
	}

	//键盘事件
	function doKeypress(event) {
		if (event.keyCode == "13"||event.keyCode == "108"){
			findObjectByInput();
		}
	}

	//点击删除事件
	function doDeleteById() {
		let user=$(this).parent().parent().data("user");
		let id=user.id;
		//console.log(id);
		//confirm("是否永久删除  "+user.name);

		let url="doDeleteById";
		$.ajax({
			type:"get",
			url:url,
			data:{"id":id},
			success:function (result) {
				if(result.data==1){
					Alert($("body"),100,"删除成功"+user.name);
				}else {

				}
			}
		});
	}

	//修改事件
	function doUpdateById() {
		console.log("修改");
		let tr = $(this).parent().parent();
		let id=tr.data("user").id;
		console.log(id);
	}

	//注册事件
	function doRegister() {
		console.log("注册")
	}

	//弹窗事件（绑在什么元素上，弹窗宽度，弹窗内容）
	function Alert(element,pWidth,content) {
		//$("#msg").remove();
		let alertWidth = element.width()/2-pWidth/2;
		//弹窗
		let html =`<div class="msg"
	style="
	position:fixed;
	top:15%;width:100%;height:10px;line-height:30px;
	margin-top:-15px;z-index: 99999;">

		<p style="background:#fff;
			opacity:0.8;width:\${pWidth}px;
			color:#000;
			text-align:center;
			padding:5px 5px;
			margin:0  \${alertWidth}px;
			left: 50%;
			font-size:12px;
			border-radius:4px;
			border: 1px solid #ccc">\${content}
		</p></div>`
		element.append(html);
		//使弹窗消失
		$(".msg").fadeOut("slow");

	}


</script>
</html>