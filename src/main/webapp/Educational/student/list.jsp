<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>学生信息管理平台</title>
<link href="../../Style/StudentStyle.css" rel="stylesheet" type="text/css" />
<link href="../../Script/jBox/Skins/Blue/jbox.css" rel="stylesheet" type="text/css" />
<link href="../../Style/ks.css" rel="stylesheet" type="text/css" />
<link href="../../css/mine.css" type="text/css" rel="stylesheet">
<script src="../../Script/jBox/jquery-1.4.2.min.js" type="text/javascript"></script>
<script src="../../Script/jBox/jquery.jBox-2.3.min.js"
	type="text/javascript"></script>
<script src="../../Script/jBox/i18n/jquery.jBox-zh-CN.js"
	type="text/javascript"></script>
<script src="../../Script/Common.js" type="text/javascript"></script>
<script src="../../Script/Data.js" type="text/javascript"></script>

<script>
	function del(){
		confirm("确认操作？");
	}

</script>



</head>
<body>

	<div class="div_head" style="width: 100%;text-align:center;">
		<span>
                <span style="float: left;">当前位置是：教务中心-》学生管理</span>
                <span style="float: right; margin-right: 8px; font-weight: bold;">
                    <a style="text-decoration: none;" href="/Educational/student?method=add">【新增学生】</a>&emsp;&emsp;&emsp;&emsp;
                </span>
            </span>
	</div>

	<div class="cztable">
		<div>
				  <form action="/Educational/student/search" method="get">
                    学生名称:
					<input type="text" name="stuName" value="${stuName}" />
                     学生学号:
					<input type="text" name="stuNo" value="${stuNo}"/>
					性别:
					<select name="sex">
							<option value="-1" ${sex==-1?'selected':''}>--请选择--</option>
							<option value="1" ${sex==1?'selected':''}>男</option>
							<option value="0" ${sex==0?'selected':''}>女</option>
						</select>
					<input type="submit" value="查询" />

                </form>



		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tbody>
				<tr style="height: 25px" align="center">
                        <th >学号</th>
						<th width="">姓名</th>
						<th width="">性别</th>
                        <th width="15%">联系电话</th>
                        <th width="15%">专业</th>
						<th width="15%">登记时间</th>
						<th>操作</th>
                    </tr>

				<c:forEach items="${stuList}" varStatus="st" var="stu">
					<tr id="product1">
						<td align="center">${stu.stuNo}</td>
						<td align="center">${stu.stuName}</td>
						<td align="center">${stu.sex==1?'男':'女'}</td>
						<td align="center">${stu.phone}</td>
						<td align="center">${stu.profession}</td>
						<%--格式化输出时间--%>
						<td align="center">
							<fmt:formatDate value="${stu.regDate}" pattern="yyyy-MM-dd"></fmt:formatDate>
						</td>
						<td align="center">
							<a href="/Educational/student?method=findById&stuId=${stu.stuId}">修改</a>
							<a href="/Educational/student?method=deleteById&stuId=${stu.stuId}">删除</a>
						</td>
					</tr>
				</c:forEach>


                    <tr>
                        <td colspan="20" style="text-align: center;">
							<a style="text-decoration: none;" href="/Educational/student/search?stuName=${stuName}&stuNo=${stuNo}&sex=${sex}">首页</a>
							<a style="text-decoration: none;" href="/Educational/student/search?pageIndex=${pageIndex-1<=1?1:pageIndex-1}&stuName=${stuName}&stuNo=${stuNo}&sex=${sex}">上一页</a>
							<a style="text-decoration: none;" href="/Educational/student/search?pageIndex=${pageIndex+1>=totalPages?totalPages:pageIndex+1}&stuName=${stuName}&stuNo=${stuNo}&sex=${sex}">下一页</a>
							<a style="text-decoration: none;" href="/Educational/student/search?pageIndex=${totalPages}&stuName=${stuName}&stuNo=${stuNo}&sex=${sex}">尾页</a>
							共${total}条
							每页显示
							${pageIndex}/${totalPages}
                        </td>
                    </tr>
                </tbody>
            </table>
	</div>

	</div>
	</div>

	</div>
</body>
</html>
