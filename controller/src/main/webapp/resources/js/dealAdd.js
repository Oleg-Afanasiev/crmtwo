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

function setCountDockContacts() {

    var div_container = document.getElementById("dock_contacts_container");
    var el_ul = document.getElementById("dock_contacts");
    var n_li = countLiItems(el_ul);
    var span_count = document.getElementById("count_dock_contacts");

    span_count.innerHTML = n_li;

    console.error("document.dealAdd.count_dock_contacts: ", document.dealAdd.count_dock_contacts);
    document.dealAdd.count_dock_contacts.value = n_li;

    if (n_li == "0")
        div_container.setAttribute("class", "hidden");
    else
        div_container.setAttribute("class", "visible");

    reSetNameAttribute(n_li);
}

function closeDockContactClick() {
    var currentLi = this.parentNode.parentNode;
    var parent_ul = currentLi.parentNode;

    parent_ul.removeChild(currentLi);
    setCountDockContacts();
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

function cancelContactClick() {
    var div_container = document.getElementById("add_contact_container");
    var div_sub_container = document.getElementById("sub_add_contact");

    div_container.setAttribute("class", "hidden");
    div_sub_container.setAttribute("class", "none");
}

function addCompanyClick() {

    var div_container = document.getElementById("add_company_container");
    var div_sub_container = document.getElementById("sub_add_company");

    div_container.setAttribute("class", "dialog");
    div_sub_container.setAttribute("class", "sub-add-content");
}

function cancelCompanyClick() {
    var div_container = document.getElementById("add_company_container");
    var div_sub_container = document.getElementById("sub_add_company");

    div_container.setAttribute("class", "hidden");
    div_sub_container.setAttribute("class", "none");
}

function changeContact(event) {

    var id = this.value;
    var contactName = this.options[this.selectedIndex].text;

    if (!isContactDocked(id))
        dockContact(id, contactName);

    setCountDockContacts();
}

window.onload = function() {

    document.getElementById("add_contact_button").onclick = addContactClick;
    document.getElementById("cancel_contact_button").onclick = cancelContactClick;
    document.getElementById("add_cancel_contact_button").onclick = cancelContactClick;

    document.getElementById("add_company_button").onclick = addCompanyClick;
    document.getElementById("cancel_company_button").onclick = cancelCompanyClick;
    document.getElementById("add_cancel_company_button").onclick = cancelCompanyClick;

    document.getElementById("exist-contact").onchange = changeContact;
}
