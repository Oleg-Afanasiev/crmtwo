/**
 * Created by oleg on 9/22/15.
 */
function showMessage(id, msg) {
    $("#" + id).tooltip({
        title: msg,
        trigger: "manual",
        placement: "right",
        animation: true
    });
    $("#" + id).tooltip('show');

    setTimeout( function() {
            $("#" + id).tooltip('destroy')
        },
        2000
    );
}

function isSelected(id) {
    var val = $("#" + id).val();

    return val > 0;
}

// ------------------DEAL--------------------
function resetFieldsDeal() {
    $("#attached_contacts").empty();
    updateSumAttachedContacts();

    $("#attached_company").empty();
    $("#attached_company_container").hide();
}

// ----------------CONTACT--------------------------------------

function isAttachedContact() {
    var idContact;
    var $inputList;

    idContact = $("#contact option:selected").val();
    $inputList = $("#attached_contacts > div");

    for(var i = 0; i < $inputList.size(); i++) {
        if ($inputList.eq(i).find("input").val() == idContact)
            return true;
    }

    return false;
}

function countAttachedContacts() {
    return $("#attached_contacts").children('div').size();
}

function updateSumAttachedContacts() {
    var nContacts;

    nContacts = countAttachedContacts();
    $("#n_attached_contacts").html(nContacts);
    $('input[name="n_attached_contacts"]').val(nContacts);

    if (nContacts > 0)
        $("#attached_contacts_container").show();
    else
        $("#attached_contacts_container").hide();
}

function renumberAttachedContacts() {
    var nContacts;
    var $listInput;

    nContacts = countAttachedContacts();
    $listInput = $("#attached_contacts > div");

    for (var i = 0; i < nContacts; i++) {
        $listInput.eq(i).find("input").eq(0).attr("name", "attached_contact_id_" + (i + 1));
        $listInput.eq(i).find("input").eq(1).attr("name", "attached_contact_name_" + (i + 1));
        $listInput.eq(i).attr("id", "attached_contact_alert_" + (i + 1));
        $listInput.eq(i).bind("closed.bs.alert", onAttachedContactClose);
    }
}

function attacheSelectedContact() {
    var contactName;
    var contactId;
    var $contactOptionEl;
    var $divItem;

    $contactOptionEl = $("#contact option:selected");

    contactName = $contactOptionEl.text().trim();
    contactId = $contactOptionEl.val();

    $divItem = $("#attached_contact_item > div").clone();
    $divItem.find("span").html(contactName);
    $divItem.find("input").eq(0).val(contactId);
    $divItem.find("input").eq(1).val(contactName);

    $("#attached_contacts").append($divItem);
    updateSumAttachedContacts();
    renumberAttachedContacts();
}

function resetFieldsContact() {
    $("#contact").val("0");
}

function resetFieldsAddContact() {
    $("#add_contact_container").find("input:text").val("");
    $("#add_contact_company_name").val("0");
    $("#add_contact_phone_type").val("1");
}

// -------------COMPANY-------------------
function isAttachedCompany() {
    return $("#attached_company > div").size() > 0;
}

function attacheSelectedCompany() {
    var companyName;
    var companyId;
    var $companyOptionEl;
    var $divItem;

    $companyOptionEl = $("#company option:selected");

    companyName = $companyOptionEl.text().trim();
    companyId = $companyOptionEl.val();

    $divItem = $("#attached_company_item > div").clone();
    $divItem.find("span").html(companyName);
    $divItem.find("input").eq(0).attr("name", "attached_company_id");
    $divItem.find("input").eq(0).val(companyId);
    $divItem.find("input").eq(1).attr("name", "attached_company_name");
    $divItem.find("input").eq(1).val(companyName);
    $divItem.bind("closed.bs.alert", onAttachedCompanyClose);

    $("#attached_company").append($divItem);
    $("#attached_company_container").show();
}

function resetFieldsCompany() {
    $("#company").val("0");
}

function resetFieldsAddCompany() {
    $("#add_company_container").find("input:text, textarea").val("");
    $("#add_company_phone_type").val("1");
}

// -----------FILES---------------
function nAttachedFiles() {
    return $("#attached_files_list > li:visible").size();
}

function updateSumAttachedFiles() {
    var nFiles;

    nFiles = nAttachedFiles();
    $("#n_uploaded_files").html(nFiles);
    $("input[name='n_uploaded_files']").val(nFiles);
}

function renumberAttachedFiles () {
    var $liList;

    $liList = $("#attached_files_list > li:visible");

    for (var i = 0; i < $liList.size(); i++) {
        $liList.eq(i).find("> div > input").attr("name", "attached_file_" + (i + 1));
    }
}

function removeEmptyFileFields() {
    var $liList;

    $liList = $("#attached_files_list > li:hidden");

    for (var i = 0; i < $liList.size(); i++)
        $liList.eq(i).remove();
}

function resetFiles() {
    $("#attached_files_list").empty();
    updateSumAttachedFiles();
}

// -----------EVENTS--------------
function addContactClick() {
    var idSelect = "contact";

    if (!isSelected(idSelect)) {
        showMessage(idSelect, "Необходимо выбрать контакт!")
    } else if (isAttachedContact()){
        showMessage(idSelect, "Этот контакт уже прикреплен к сделке!");
    } else {
        attacheSelectedContact();
        showMessage(idSelect, "Контакт успешно прикреплен!");
    }
}

function onAttachedContactClose() {
    renumberAttachedContacts();
    updateSumAttachedContacts();
}

function addCompanyClick() {
    var idSelect = "company";

    if (!isSelected(idSelect)) {
        showMessage(idSelect, "Необходимо выбрать компанию!")
    } else if (isAttachedCompany()) {
        showMessage(idSelect, "К сделке можно прикрепить только одну компанию!");
    } else {
        attacheSelectedCompany();
        showMessage(idSelect, "Компания успешно прикреплена!")
    }
}

function onAttachedCompanyClose() {
    $("#attached_company_container").hide();
}

function onChangeInputFile() {
    var $inputFile;
    var fileName;

    $inputFile = $(this);

    fileName = $inputFile[0].files[0].name
    $inputFile.prev("span").html(fileName);
    $inputFile.parent().parent().show(); // <li>...</li>

    removeEmptyFileFields();
    updateSumAttachedFiles();
    renumberAttachedFiles();
}

function onUnAttachFileClick() {
    var $liItem;

    $liItem = $(this).parent().parent();
    $liItem.remove();

    updateSumAttachedFiles();
    renumberAttachedFiles();
}

function addFileClick() {
    var $ulContainer;
    var $liItem;
    var $inputFile;

    $ulContainer = $("#attached_files_list");
    $liItem = $("#attached_file_li_item > li").clone();
    $ulContainer.append($liItem);
    $liItem.hide();
    $inputFile = $liItem.find('input');
    $inputFile.click();

    $inputFile.hide();
    $inputFile.change(onChangeInputFile);
    $liItem.find("div > a.close").click(onUnAttachFileClick);
}

function cancelBtnClick() {
    resetFieldsDeal();
    resetFiles();

    resetFieldsAddContact();
    resetFieldsContact();
    resetFieldsAddCompany();
    resetFieldsCompany();
}

function addContactCancelBtnClick() {
    resetFieldsAddContact();
}

function contactCancelBtnClick() {
    resetFieldsContact();
}

function addCompanyCancelBtnClick() {
    resetFieldsAddCompany();
}

function companyCancelBtnClick() {
    resetFieldsCompany();
}

function onLoad() {
    $("#contact_add_button").click(addContactClick);
    $("#company_add_button").click(addCompanyClick);
    $("#add_files_btn").click(addFileClick);
    $("#cancel_button").click(cancelBtnClick);
    $("#company_cancel_button").click(companyCancelBtnClick);
    $("#add_company_cancel_button").click(addCompanyCancelBtnClick);
    $("#add_contact_cancel_button").click(addContactCancelBtnClick);
    $("#contact_cancel_button").click(contactCancelBtnClick);

    updateSumAttachedContacts();
    renumberAttachedContacts();

    if ($("#attached_company > div").size() > 0) {
        $("#attached_company_container").show();

    }

    //$("#add_contact_button").on("click", addContactClick);
}

$(onLoad);

