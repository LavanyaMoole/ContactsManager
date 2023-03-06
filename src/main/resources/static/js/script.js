//toggle bar in dashbase page
const toggleSidebar = () => {

	if ($('.sidebar').is(":visible"))
	{
		$(".sidebar").css("display","none");
		$(".content").css("margin-left","0%");
	}
	else
	{
		$(".sidebar").css("display","block");
		$(".content").css("margin-left","20%");
	}

};

//delete contact alert
function deleteAlert(){

alert("Are you sure you want to delete this contact!!!");


};

function confirm_Delete(){
let res=confirm("Are you sure you want delete?");
if(res==true){
return true;}
else{
return false;}
}
