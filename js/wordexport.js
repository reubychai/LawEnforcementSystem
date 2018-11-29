/* Reuby
 * 此方法实现的功能为生成报告及其下载：
 *  1.获取网页所需内容，并传给后端；
 *  2.获取后端Response，生成下载链接；
 *  3.具体变量定义可参考dataprocess.js。 
 */
function downloadDoc()
{
var check4=document.getElementsByName("zfry");      
var check5=document.getElementsByName("syfw1");   
var check7=document.getElementsByName("syfw3");    
var check8=document.getElementsByName("wglx");   

var upfloder = document.getElementById('mission').contentWindow.document.getElementById('floder').innerHTML;

var r1,r3,t;
var p = [];
for (l=0;l<check4.length;l++){
    if (check4[l].checked == true){
    p.push(check4[l].value);}else{}}    
   
for(x=0;x<check5.length;x++){
	if(check5[x].checked == true){
		r1=check5[x].id;}else{}}

for(y=0;y<check7.length;y++){
	if(check7[y].checked == true){
		r3=check7[y].id;}else{}}

for(z=0;z<check8.length;z++){
	if(check8[z].checked == true){
		t=check8[z].id;}else{}}

$.ajax({
       type : "get",      
	   async : true,            
	   url : '/LawEnforcementSystem/WordExport',            
	   data : {"type":t,"range":r3,"staff[]":p,"floder":upfloder},  //要传给servlet
	   dataType : "text",        //返回数据形式为纯文本字符串  
	   success : function (result) {
		     $("#message").html(result);
		     
    },
      error: function (errorMsg) {                                           
      alert("error!");}
           })

}