package com.becomejavasenior.impl.jdbc;

import com.becomejavasenior.*;
import com.becomejavasenior.impl.DaoUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Dmytro Tsapko on 9/15/2015.
 */
public enum CommandMethod {

    getPhones("getPhones"),
    getDeals("getDeals"),
    getTags("getTags"),
    getFiles("getFiles"),
    getComments("getComments"),
    getResponsibleUser("getResponsibleUser"),
    getDealStatus("getDealStatus"),
    getCompanies("getCompanies"),
    getContacts("getContacts"),
    getCompany("getCompany"),
    getRole("getRole"),
    getDeal("getDeal"),
    getContact("getContact"),
    getTaskType("getTaskType"),
    getTaskPeriod("getTaskPeriod"),
    getPassword("getPassword"),
    setResponsibleUser("setResponsibleUser"),
    setDealStatus("setDealStatus"),
    setName("setName"),
    setBudget("setBudget"),
    setCreated("setCreated"),
    setUpdated("setUpdated"),
    setTags("setTags"),
    setFiles("setFiles"),
    setComments("setComments"),
    setContacts("setContacts"),
    setCompanies("setCompanies"),
    setEmail("setEmail"),
    setWebAdress("setWebAddress"),
    setAdress("setAddress"),
    setPhones("setPhones"),
    setDeals("setDeals"),
    setJobPosition("setJobPosition"),
    setSkype("setSkype"),
    setPath("setPath"),
    setMimeType("setMimeType"),
    setNumber("setNumber"),
    setPhoneType("setPhoneType"),
    setTaskPeriod("setTaskPeriod"),
    setDescription("setDescription"),
    setTaskType("setTaskType"),
    setCompany("setCompany"),
    setDeal("setDeal"),
    setContact("setContact"),
    setDueDate("setDueDate"),
    setRole("setRole"),
    setFirstName("setFirstName"),
    setLastName("setLastName"),
    setUserName("setUserName"),
    setPassword("setPassword")
    ;

    private String name;
    private Class entityClass;
    private String field;
    private AbstractDAO dao;
    private boolean usesCollection;
    private Collection<Long> relatedIDs;

    private static final Map<String, Class> methodToEntity;
    static {
        Map<String, Class> tempMethodToEntity = new HashMap<>();
        tempMethodToEntity.put("getPhones", Phone.class);
        tempMethodToEntity.put("getDeals", Deal.class);
        tempMethodToEntity.put("getTags", Tag.class);
        tempMethodToEntity.put("getFiles", File.class);
        tempMethodToEntity.put("getComments", Comment.class);
        tempMethodToEntity.put("getResponsibleUser", User.class);
        tempMethodToEntity.put("getDealStatus", DealStatus.class);
        tempMethodToEntity.put("getCompanies", Company.class);
        tempMethodToEntity.put("getContacts", Contact.class);
        tempMethodToEntity.put("getCompany", Company.class);
        tempMethodToEntity.put("getRole", Role.class);
        tempMethodToEntity.put("getDeal", Deal.class);
        tempMethodToEntity.put("getContact", Contact.class);
        tempMethodToEntity.put("getTaskType", TaskType.class);
        tempMethodToEntity.put("getTaskPeriod", TaskPeriod.class);
        methodToEntity = Collections.unmodifiableMap(tempMethodToEntity);
    }

    private static final Map<String, Boolean> methodUsesCollection;
    static {
        Map<String, Boolean> tempMethodUsesCollection = new HashMap<>();
        tempMethodUsesCollection.put("getPhones", true);
        tempMethodUsesCollection.put("getDeals", true);
        tempMethodUsesCollection.put("getTags", true);
        tempMethodUsesCollection.put("getFiles", true);
        tempMethodUsesCollection.put("getComments", true);
        tempMethodUsesCollection.put("getResponsibleUser", false);
        tempMethodUsesCollection.put("getDealStatus", false);
        tempMethodUsesCollection.put("getCompanies", true);
        tempMethodUsesCollection.put("getContacts", true);
        tempMethodUsesCollection.put("getCompany", false);
        tempMethodUsesCollection.put("getRole", false);
        tempMethodUsesCollection.put("getDeal", false);
        tempMethodUsesCollection.put("getContact", false);
        tempMethodUsesCollection.put("getTaskType", false);
        tempMethodUsesCollection.put("getTaskPeriod", false);
        methodUsesCollection = Collections.unmodifiableMap(tempMethodUsesCollection);
    }

    private static final Map<String, String> methodToField;
    static {
        Map<String, String> tempMethodToField = new HashMap<>();
        tempMethodToField.put("getPhones", "phones");
        tempMethodToField.put("getDeals", "deals");
        tempMethodToField.put("getTags", "tags");
        tempMethodToField.put("getFiles", "files");
        tempMethodToField.put("getComments", "comments");
        tempMethodToField.put("getResponsibleUser", "responsibleUser");
        tempMethodToField.put("getDealStatus", "dealStatus");
        tempMethodToField.put("getCompanies", "companies");
        tempMethodToField.put("getContacts", "contacts");
        tempMethodToField.put("getCompany", "company");
        tempMethodToField.put("getRole", "role");
        tempMethodToField.put("getDeal", "deal");
        tempMethodToField.put("getContact", "contact");
        tempMethodToField.put("getTaskType", "taskType");
        tempMethodToField.put("getTaskPeriod", "taskPeriod");
        methodToField = Collections.unmodifiableMap(tempMethodToField);
    }

    private Class getEntityClass(String name){
        return methodToEntity.get(name);
    }
    private boolean usesCollection(String name){
        return methodUsesCollection.get(name);
    }
    private String getFieldByMethod(String name){
        return methodToField.get(name);
    }

    private CommandMethod(String name){
        this.name = name;
    }

    public CommandMethod init(){ //todo how I can move all this stuff into constructor?
        this.entityClass = getEntityClass(name);
        if (entityClass != null) {
            this.field = getFieldByMethod(name);
            this.usesCollection = usesCollection(name);
            this.dao = DaoManager.getInstance().getDaoByClass(this.entityClass);
        }

        return this;
    }

    public Object execute(Method method, Identity instance, Object[] args) throws InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Object result = null;
        if(!this.relatedIDs.isEmpty()){
            if(this.usesCollection){
                List list = new ArrayList();
                for (Long id : this.relatedIDs) {
                    list.add(this.dao.getById(id));
                }
                result = list;
            }else {
                result = this.dao.getById(this.relatedIDs.iterator().next());
            }
        }else {
            result = method.invoke(instance, args);
        }
        if (this.field != null) {
            DaoUtils.setPrivateField(instance, this.field, result);
        }
        return result;
    }

    public CommandMethod setRelatedIDs(Collection<Long> iDs){
        this.relatedIDs = iDs;
        return this;
    }
}
