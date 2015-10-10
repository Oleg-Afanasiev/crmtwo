/**
 * Created by oleg on 9/22/15.
 */
function countLiItems(ul) {
    var i = 0;
    var itemCount = 0;

    while(ul.getElementsByTagName('li')[i++])
        itemCount++;

    return itemCount;
}

function reSetNameAttribute(n) {
    var parent_ul = document.getElementById("dock_contacts");
    var list_input = parent_ul.getElementsByTagName("input");

    for (var i = 0; i < list_input.length; i++) {
        if (list_input[i].getAttribute("type") == "hidden") {
            list_input[i].name = "dock_contact_" + (i + 1).toString();
        }
    }
}

function updateCountDockContacts() {

    var div_container = document.getElementById("dock_contacts_container");
    var el_ul = document.getElementById("dock_contacts");
    var n_li = countLiItems(el_ul);
    var span_count = document.getElementById("count_dock_contacts");

    span_count.innerHTML = n_li;

    document.dealAdd.count_dock_contacts.value = n_li;

    if (n_li == "0")
        div_container.setAttribute("class", "hidden");
    else
        div_container.setAttribute("class", "visible");

    reSetNameAttribute(n_li);
}

function initUnDockContactClick() {
    var parentNode = document.getElementById("dock_contacts");
    var aList = parentNode.getElementsByTagName("a");

    for (var item in aList) {
        if (aList[item].innerHTML == "x")
            aList[item].onclick  = closeDockContactClick;
    }
}

function closeDockContactClick() {
    var currentLi = this.parentNode.parentNode;
    var parent_ul = currentLi.parentNode;

    parent_ul.removeChild(currentLi);
    updateCountDockContacts();
}

function isContactDocked(id) {

    var section_target = document.getElementById("id_contact_" + id);

    return section_target != null;
}

function dockContact(id, contactName) {

    var el_ul = document.getElementById("dock_contacts");
    var new_node_li = document.createElement("li");
    var new_node_section = document.createElement("section");
    var new_node_contact = document.createElement("a");
    var new_node_close = document.createElement("a");
    var new_node_input = document.createElement("input");

    new_node_li.setAttribute("class", "li-dock-contact");

    new_node_section.setAttribute("id", "id_contact_" + id);

    new_node_contact.setAttribute("class", "a-contact");
    new_node_contact.setAttribute("href", "#");
    new_node_contact.innerHTML = contactName;

    new_node_close.setAttribute("class", "a-undock");
    new_node_close.setAttribute("title", "Открепить контакт");
    new_node_close.setAttribute("href", "#");
    new_node_close.innerHTML = "x";
    new_node_close.onclick = closeDockContactClick;

    new_node_input.setAttribute("name", "dock_contact_1");
    new_node_input.setAttribute("type", "hidden");
    new_node_input.setAttribute("value", id);

    new_node_section.appendChild(new_node_close);
    new_node_section.appendChild(new_node_contact);
    new_node_section.appendChild(new_node_input);

    new_node_li.appendChild(new_node_section);
    el_ul.appendChild(new_node_li);

}

function addContactClick() {
    var div_container = document.getElementById("add_contact_container");
    var div_sub_container = document.getElementById("sub_add_contact");

    div_container.setAttribute("class", "dialog");
    div_sub_container.setAttribute("class", "sub-add-content");
}

function addCancelContactClick() {
    var div_container = document.getElementById("add_contact_container");
    var div_sub_container = document.getElementById("sub_add_contact");

    div_container.setAttribute("class", "hidden");
    div_sub_container.setAttribute("class", "none");
}

function cancelContactClick() {
    var selectEl = document.getElementById("exist-contact");
    var dockUl = document.getElementById("dock_contacts");

    selectEl.selectedIndex = 0;

    while (dockUl.firstChild) {
        dockUl.removeChild(dockUl.firstChild);
    }
    updateCountDockContacts();
}

function selectByValue(id, value, callOnChange) {
    var selectEl = document.getElementById(id);

    for (var i = 0; i < selectEl.options.length; i++) {
        if (selectEl.options[i].value == value) {
            selectEl.options[i].selected = true;
            break;
        }
    }

    if (callOnChange)
        selectEl.onchange(); //
}

function insertIntoSelect(id, value, text, doSelect, callOnChange) {
    var selectEl = document.getElementById(id);
    var optionEl = document.createElement("option");

    optionEl.value = value;
    optionEl.text = text;
    selectEl.add(optionEl);

    if (doSelect)
        selectByValue(id, value, callOnChange);
 }

function readContactData() {

    var index;
    var contactData = {};

    contactData.name = document.getElementById("add_contact_name").value;

    index = document.getElementById("add_contact_company_name").selectedIndex;
    contactData.companyId = document.getElementById("add_contact_company_name").options[index].value;

    contactData.jobPosition = document.getElementById("add_contact_job_position").value;

    index = document.getElementById("add_contact_phone_type").selectedIndex;
    contactData.phoneTypeId = document.getElementById("add_contact_phone_type").options[index].value;

    contactData.phoneNumber = document.getElementById("add_contact_phone_number").value;
    contactData.email = document.getElementById("add_contact_email").value;
    contactData.skype = document.getElementById("add_contact_skype").value;

    return contactData;
}

function readCompanyData() {
    var index;
    var companyData = {};

    companyData.name = document.getElementById("add_company_name").value;

    index = document.getElementById("add_company_phone_type").selectedIndex;
    companyData.phoneTypeId = document.getElementById("add_company_phone_type").options[index].value;

    companyData.phoneNumber = document.getElementById("add_company_phone_number").value;
    companyData.email = document.getElementById("add_company_email").value;
    companyData.webAddress = document.getElementById("add_company_web_address").value;
    companyData.address = document.getElementById("add_company_address").value;

    return companyData;
}

function SaveNewEntity(newEntity, idUl, idCountContainer, nameEntity) {

    var parentNodeUl = document.getElementById(idUl);
    var liNode = document.createElement("li");
    var count = countLiItems(parentNodeUl) + 1; // '... + 1' - because newEntity wasn't append
    var inputEl;
    console.log("newEntity: ", newEntity);

    newEntity['pseudoId'] = -count;

    for (var item in newEntity) {
        //console.log("item: ", item);
        inputEl = document.createElement("input");
        inputEl.setAttribute("type", "hidden");
        inputEl.setAttribute("name", nameEntity + "_" + item.toString() + "_" + count.toString());
        inputEl.value = newEntity[item];
        liNode.appendChild(inputEl);
    }

    parentNodeUl.appendChild(liNode);

    return count;
}

function updateCountNewContacts() {
    var parentNodeUl = document.getElementById("added_contact_list");
    var countContacts = countLiItems(parentNodeUl);
    document.getElementById("count_new_contacts").value = countContacts;
}

function add_save_contact_button() {

    var contactData = readContactData();
    var countContacts = SaveNewEntity(contactData, "added_contact_list", "count_new_contacts", "contact");
    addCancelContactClick();
    insertIntoSelect("exist-contact", -countContacts, contactData.name, true, true);
    updateCountNewContacts();
}

function updateCountNewCompanies() {
    var parentNodeUl = document.getElementById("added_company_list");
    var countCompanies = countLiItems(parentNodeUl);
    document.getElementById("count_new_companies").value = countCompanies;
}

function add_save_company_button() {
    var companyData = readCompanyData();
    var countCompanies = SaveNewEntity(companyData, "added_company_list", "count_new_companies", "company");
    addCancelCompanyClick();

    insertIntoSelect("company", parseInt(-countCompanies), companyData.name, true, false);
    insertIntoSelect("add_contact_company_name", parseInt(-countCompanies), companyData.name, false, false);

    updateCountNewCompanies();
}

function addCompanyClick() {

    var div_container = document.getElementById("add_company_container");
    var div_sub_container = document.getElementById("sub_add_company");

    div_container.setAttribute("class", "dialog");
    div_sub_container.setAttribute("class", "sub-add-content");
}

function addCancelCompanyClick() {
    var div_container = document.getElementById("add_company_container");
    var div_sub_container = document.getElementById("sub_add_company");

    div_container.setAttribute("class", "hidden");
    div_sub_container.setAttribute("class", "none");
}

function cancelCompanyClick() {
    var selectEl = document.getElementById("company");

    selectEl.selectedIndex = 0;
}

function changeContact(event) {

    var id = this.value;
    var contactName = this.options[this.selectedIndex].text;

    if (!isContactDocked(id) && id != 0)
        dockContact(id, contactName);

    updateCountDockContacts();
}

function setDefaultPhoneType(name) {
    var selectList = [];

    selectList.push(document.getElementById("add_contact_phone_type"));
    selectList.push(document.getElementById("add_company_phone_type"));

    for (var i = 0; i < selectList.length; i++)
        for (var j = 0; j < selectList[i].options.length; j++)
            if (selectList[i].options[j].innerHTML == name)
                selectList[i].options[j].selected = true;
}

function cancelClick() {
    cancelContactClick();
    fileCancelClick();
}


window.onload = function() {

    document.getElementById("add_contact_button").onclick = addContactClick;
    document.getElementById("cancel_contact_button").onclick = cancelContactClick;
    document.getElementById("add_cancel_contact_button").onclick = addCancelContactClick;
    document.getElementById("add_save_contact_button").onclick = add_save_contact_button;

    document.getElementById("add_company_button").onclick = addCompanyClick;
    document.getElementById("cancel_company_button").onclick = cancelCompanyClick;
    document.getElementById("add_cancel_company_button").onclick = addCancelCompanyClick;
    document.getElementById("add_save_company_button").onclick = add_save_company_button;

    document.getElementById("exist-contact").onchange = changeContact;

    setDefaultPhoneType("Рабочий");

    document.getElementById("cancel_button").onclick = cancelClick;

    updateCountDockContacts();
    updateCountNewCompanies();
    updateCountNewContacts();

    initUnDockContactClick();

    fileUploadMain();
}
