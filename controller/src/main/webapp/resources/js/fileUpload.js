/**
 * Created by oleg on 10/7/15.
 */

function getCountUploadedFiles() {

    var ul = document.getElementById("uploaded_files_list");

    var i = 0;
    var itemCount = 0;

    while(ul.getElementsByTagName('li')[i++])
        itemCount++;

    return itemCount;
}

function updateCountFiles() {

    var parentEl = document.getElementById("total_uploaded_files");
    var count = getCountUploadedFiles();

    document.getElementById("count_uploaded_files").innerHTML = count;
    document.dealAdd.count_uploaded_files.value = count;

    if (count == 0)
        parentEl.setAttribute("class", "hidden");
    else
        parentEl.setAttribute("class", "visible");

    return count;
}

function OnChangeFileUploaded() {
    var countExistsFiles = getCountUploadedFiles();
    var ul = document.getElementById("uploaded_files_list");
    var li = document.createElement("li");
    var fileName = this.value;

    fileName = fileName.replace(/.*[\/\\]/, '');
    li.id = "file_input_" + (countExistsFiles + 1);
    ul.appendChild(li);
    li.innerHTML = fileName;

    updateCountFiles();

    console.log("li: ", li);
    console.log("this.value: ", fileName);
}

function addFilesClick() {

    var divContainer = document.getElementById("uploaded_files_container");
    var countExistsFiles = getCountUploadedFiles();
    var input = document.getElementsByName("file_input_" + (countExistsFiles + 1).toString())[0];

    if (input == null)
        input = document.createElement("input");

    input.type = "file";
    input.setAttribute("class", "hidden");
    input.name = "file_input_" + (countExistsFiles + 1);

    divContainer.appendChild(input);

    input.onchange = OnChangeFileUploaded;
    input.click();
}

function fileCancelClick() {
    var ul = document.getElementById("uploaded_files_list");
    var container = document.getElementById("uploaded_files_container");

    ul.innerHTML = "";
    container.innerHTML = "";

    updateCountFiles();
}

function fileUploadMain() {
    document.getElementById("add_files_btn").onclick = addFilesClick;
}