package com.becomejavasenior;

import com.becomejavasenior.impl.jdbc.*;
import org.apache.commons.dbcp2.BasicDataSource;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Dmytro Tsapko on 8/29/2015.
 */
public class DaoManager {

    private static BasicDataSource connectionPool;
    private static ThreadLocal<DaoManager> daoManagerThreadLocal = new ThreadLocal<>();

    private Connection connection;
    private DealDAO dealDAO;
    private DealStatusDAO dealStatusDAO;
    private TaskDAO taskDAO;
    private TaskPeriodDAO taskPeriodDAO;
    private TaskTypeDAO taskTypeDAO;
    private CommentDAO commentDAO;
    private CompanyDAO companyDAO;
    private ContactDAO contactDAO;
    private FileDAO fileDAO;
    private PhoneDAO phoneDAO;
    private TagDAO tagDAO;
    private UserDAO userDAO;
    private RoleDAO roleDAO;

    static {
        String username;
        String password;
        String dbUrl;
        String driver;
        String dbUrlFromEnv = System.getenv("DATABASE_URL"); // It is convenient to use this on Heroku platform
        if (dbUrlFromEnv != null) {
            try {
                URI dbUri = new URI(dbUrlFromEnv);
                username = dbUri.getUserInfo().split(":")[0];
                password = dbUri.getUserInfo().split(":")[1];
                dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();
                driver = "org.postgresql.Driver";
            } catch (URISyntaxException e) {
                throw new DAOException("can't get Database URL from Env variable.", e);
            }
        } else {
            DaoProperties daoProperties = new DaoProperties();
            username = daoProperties.getProperty("user");
            password = daoProperties.getProperty("password");
            dbUrl = daoProperties.getProperty("url");
            driver = daoProperties.getProperty("driver");
        }
        connectionPool = new BasicDataSource();
        connectionPool.setUsername(username);
        connectionPool.setPassword(password);
        connectionPool.setDriverClassName(driver);
        connectionPool.setUrl(dbUrl);
        connectionPool.setInitialSize(10);
        connectionPool.setMaxTotal(20);
    }

    public static synchronized DaoManager getInstance() {
        if(daoManagerThreadLocal.get() == null){
            daoManagerThreadLocal.set(new DaoManager());
        }
        return daoManagerThreadLocal.get();
    }


    private DaoManager() {
        try {
            this.connection = connectionPool.getConnection();
            this.connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DAOException("Can't get connection from pool.", e);
        }
    }


    public DealDAO getDealDAO() {
        if(this.dealDAO == null){
            this.dealDAO = new DealDAOImpl(connection);
        }
        return this.dealDAO;
    }

    public DealStatusDAO getDealStatusDAO() {
        if(this.dealStatusDAO == null){
            this.dealStatusDAO =  new DealStatusDAOImpl(connection);
        }
        return this.dealStatusDAO;
    }

    public TaskDAO getTaskDAO() {
        if(this.taskDAO == null){
            this.taskDAO = new TaskDAOImpl(connection);
        }
        return this.taskDAO;
    }

    public TaskPeriodDAO getTaskPeriodDAO() {
        if(this.taskPeriodDAO == null){
            this.taskPeriodDAO = new TaskPeriodDAOImpl(connection);
        }
        return this.taskPeriodDAO;
    }

    public TaskTypeDAO getTaskTypeDAO() {
        if(this.taskTypeDAO == null){
            this.taskTypeDAO = new TaskTypeDAOImpl(connection);
        }
        return this.taskTypeDAO;
    }

    public CommentDAO getCommentDAO() {
        if(this.commentDAO == null){
            this.commentDAO =  new CommentDAOImpl(connection);
        }
        return this.commentDAO;
    }

    public CompanyDAO getCompanyDAO() {
        if(this.companyDAO == null){
            this.companyDAO = new CompanyDAOImpl(connection);
        }
        return this.companyDAO;
    }

    public ContactDAO getContactDAO() {
        if(this.contactDAO == null){
            this.contactDAO = new ContactDAOImpl(connection);
        }
        return this.contactDAO;
    }

    public FileDAO getFileDAO() {
        if(this.fileDAO == null){
            this.fileDAO = new FileDAOImpl(connection);
        }
        return this.fileDAO;
    }

    public PhoneDAO getPhoneDAO() {
        if(this.phoneDAO == null){
            this.phoneDAO = new PhoneDAOImpl(connection);
        }
        return this.phoneDAO;
    }

    public TagDAO getTagDAO() {
        if(this.tagDAO == null){
            this.tagDAO = new TagDAOImpl(connection);
        }
        return this.tagDAO;
    }

    public UserDAO getUserDAO() {
        if(this.userDAO == null){
            this.userDAO =  new UserDAOImpl(connection);
        }
        return this.userDAO;
    }

    public RoleDAO getRoleDAO() {
        if(this.roleDAO == null){
            this.roleDAO = new RoleDAOImpl(connection);
        }
        return this.roleDAO;
    }

    /**
     *
     * @param clazz - interface of entity.
     * @return instance of DAO for entity which interface was passed as parameter.
     */
    public AbstractDAO getDaoByClass(Class clazz){

        if(clazz.equals(Role.class)){
            return getRoleDAO();
        }
        if(clazz.equals(User.class)){
            return getUserDAO();
        }

        if(clazz.equals(Tag.class)){
            return getTagDAO();
        }

        if(clazz.equals(Phone.class)){
            return getPhoneDAO();
        }

        if(clazz.equals(File.class)){
            return getFileDAO();
        }

        if(clazz.equals(Contact.class)){
            return getContactDAO();
        }

        if(clazz.equals(Company.class)){
            return getCompanyDAO();
        }

        if(clazz.equals(Comment.class)){
            return getCommentDAO();
        }

        if(clazz.equals(TaskType.class)){
            return getTaskTypeDAO();
        }

        if(clazz.equals(TaskPeriod.class)){
            return getTaskPeriodDAO();
        }

        if(clazz.equals(Task.class)){
            return getTaskDAO();
        }

        if(clazz.equals(DealStatus.class)){
            return getDealStatusDAO();
        }

        if(clazz.equals(Deal.class)){
            return getDealDAO();
        }

        throw new IllegalArgumentException("DAO for class " + clazz.getName() + " was not found.");
    }

    public AbstractDAO getDaoByObject(Object object){

        if(object instanceof Role){
            return getRoleDAO();
        }
        if(object instanceof User){
            return getUserDAO();
        }

        if(object instanceof Tag){
            return getTagDAO();
        }

        if(object instanceof Phone){
            return getPhoneDAO();
        }

        if(object instanceof File){
            return getFileDAO();
        }

        if(object instanceof Contact){
            return getContactDAO();
        }

        if(object instanceof Company){
            return getCompanyDAO();
        }

        if(object instanceof Comment){
            return getCommentDAO();
        }

        if(object instanceof TaskType){
            return getTaskTypeDAO();
        }

        if(object instanceof TaskPeriod){
            return getTaskPeriodDAO();
        }

        if(object instanceof Task){
            return getTaskDAO();
        }

        if(object instanceof DealStatus){
            return getDealStatusDAO();
        }

        if(object instanceof Deal){
            return getDealDAO();
        }

        throw new IllegalArgumentException("DAO for object " + object + " was not found.");
    }

    public void closeConnection() {

        try{
            this.connection.commit();
            this.connection.close();
            daoManagerThreadLocal.remove();
        }catch (SQLException e){
            throw new DAOException("Can't close connection", e);
        }
    }

    public Connection getConnection(){
        return this.connection;
    }
}
