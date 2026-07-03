function open_myname()
{


	var content_obj = document.getElementById('nicpopcontent');
	content_obj.className= "winsize2";
	content_obj.innerHTML = "My Name ";
	show_popup();
}

function open_yourname()
{
	var content_obj = document.getElementById('nicpopcontent');
	content_obj.className= "winsizeper";

str = "menu dskfjsdkflsd fksdfj sdklfjdsfkdslfj sdkfsdj fsdklfsdjafklsdfjsdkfajsadf dsfl sdfjsdkfsdjfklds ";
	str += "menu dskfjsdkflsd fksdfj sdklfjdsfkdslfj sdkfsdj fsdklfsdjafklsdfjsdkfajsadf dsfl sdfjsdkfsdjfklds ";
	str += "menu dskfjsdkflsd fksdfj sdklfjdsfkdslfj sdkfsdj fsdklfsdjafklsdfjsdkfajsadf dsfl sdfjsdkfsdjfklds ";
	str += "menu dskfjsdkflsd fksdfj sdklfjdsfkdslfj sdkfsdj fsdklfsdjafklsdfjsdkfajsadf dsfl sdfjsdkfsdjfklds ";
	str += "menu dskfjsdkflsd fksdfj sdklfjdsfkdslfj sdkfsdj fsdklfsdjafklsdfjsdkfajsadf dsfl sdfjsdkfsdjfklds ";
	str += "menu dskfjsdkflsd fksdfj sdklfjdsfkdslfj sdkfsdj fsdklfsdjafklsdfjsdkfajsadf dsfl sdfjsdkfsdjfklds ";
	str += "menu dskfjsdkflsd fksdfj sdklfjdsfkdslfj sdkfsdj fsdklfsdjafklsdfjsdkfajsadf dsfl sdfjsdkfsdjfklds ";
	str += "menu dskfjsdkflsd fksdfj sdklfjdsfkdslfj sdkfsdj fsdklfsdjafklsdfjsdkfajsadf dsfl sdfjsdkfsdjfklds ";
	str += "menu dskfjsdkflsd fksdfj sdklfjdsfkdslfj sdkfsdj fsdklfsdjafklsdfjsdkfajsadf dsfl sdfjsdkfsdjfklds ";
	str += "menu dskfjsdkflsd fksdfj sdklfjdsfkdslfj sdkfsdj fsdklfsdjafklsdfjsdkfajsadf dsfl sdfjsdkfsdjfklds <br>";
	str += "menu dskfjsdkflsd fksdfj sdklfjdsfkdslfj sdkfsdj fsdklfsdjafklsdfjsdkfajsadf dsfl sdfjsdkfsdjfklds <br>";
	str += "menu dskfjsdkflsd fksdfj sdklfjdsfkdslfj sdkfsdj fsdklfsdjafklsdfjsdkfajsadf dsfl sdfjsdkfsdjfklds <br>";
	str += "menu dskfjsdkflsd fksdfj sdklfjdsfkdslfj sdkfsdj fsdklfsdjafklsdfjsdkfajsadf dsfl sdfjsdkfsdjfklds <br>";
	str += "menu dskfjsdkflsd fksdfj sdklfjdsfkdslfj sdkfsdj fsdklfsdjafklsdfjsdkfajsadf dsfl sdfjsdkfsdjfklds <br>";

                  content_obj.innerHTML = str;

	show_popup();



}
close_popup();

function openmenu()
{

	var content_obj = document.getElementById('nicpopcontent');
	content_obj.innerHTML = "menu 2 ";
	content_obj.className= "winsize2";
	show_menupopup_arrow('menu1');
}

function openmenuarrow()
{
	var content_obj = document.getElementById('nicpopcontent');
	str = "menu dskfjsdkflsd fksdfj sdklfjdsfkd";
                  content_obj.innerHTML = str;
	content_obj.className= "winsize2";
	show_menupopup_arrow('menu2');
}