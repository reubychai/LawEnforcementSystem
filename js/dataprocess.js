/*  Reuby
 *  此方法为HXWeb中表单提交button的响应方法：
 *  1.某选择栏未被选中时，弹出相应提示框；
 *  2.完成相关文档的查询。
 */
function dataprocess()
{
var ways=[];
var regulations=[];
var laws=[];
var check1=document.getElementsByName("rwly");      //任务来源
var check2=document.getElementsByName("zflx");      //执法类型
var check3=document.getElementsByName("zfdx");      //执法对象object
var check4=document.getElementsByName("zfry");      //执法人员
var check5=document.getElementsByName("syfw1");     //水域范围range1(上海局)
var check7=document.getElementsByName("syfw3");     //水域范围range3(管理三角域)
var check8=document.getElementsByName("wglx");      //违规类型type
	    
//弹出提示框
if($(":radio[name=rwly]:checked").size() == 0)
    {
     alert("请选择任务来源！")
     }
else if($(":radio[name=zflx]:checked").size() == 0)
     {
      alert("请选择执法类型！")
      }
else if($(":radio[name=zfdx]:checked").size() == 0)
	{
	alert("请选择执法对象！")
	}
else if($(":checkbox[name=zfry]:checked").size() == 0)
    {
     alert("请选择执法人员！")
    }
else if($(":radio[name=syfw1]:checked").size() == 0)
    {
     alert("请选择上海局范围！")
     }

else if($(":radio[name=syfw3]:checked").size() == 0)
    {
    alert("请选择管理三角域！")
    }
else if($(":radio[name=wglx]:checked").size() == 0)
    {
alert("请选择违规类型！")
  }

//指定关键字(选项)
var object;
for (i=0;i<check3.length;i++){
    if (check3[i].checked == true)
    { object=check3[i].id;}else{}}

var range1;
for (j=0;j<check5.length;j++){
    if (check5[j].checked == true)
    {range1=check5[j].id;}else{}}
    
var range3;    
for (k=0;k<check7.length;k++){
    if (check7[k].checked == true){
    range3=check7[k].id;}else{}}

var type;
for (m=0;m<check8.length;m++){
    if (check8[m].checked == true){
    type=check8[m].id;}else{}}

//文档查询
  $.ajax({
	       type : "get",      
		   async : true,             
		   url : '/LawEnforcementSystem/RequestContentInfo',            
		   data : {},
		   dataType : "json",        //返回数据形式为json
		   success : function (data) {

			   for (var n=0; n<data.length; n++){
				   
				   if ((object==data[n].object) && (range1==data[n].range1) && (range3==data[n].range3) && (type==data[n].type) == true)
					   {
                       var reg=new RegExp("\r\n","g"); 
                       buff = data[n].way.replace(reg,"<br>"); 
                       ways.push(buff);
					   
                       var reg=new RegExp("\r\n","g"); 
                       buff = data[n].regulation.replace(reg,"<br>"); 
                       regulations.push(buff);
					   
                       var reg=new RegExp("\r\n","g"); 
                       buff = data[n].law.replace(reg,"<br>"); 
                       laws.push(buff);
                       
                       document.getElementById("myDiv").innerHTML=ways;	
					   document.getElementById("myDiv1").innerHTML=regulations;
                       document.getElementById("myDiv2").innerHTML=laws;					   
					   }else{}                //数据库中没有满足此条件的文档
		   		}
           },
             error: function (errorMsg) { 
             alert("访问数据出错！");}               //请求Servlet出错
                  })
}















