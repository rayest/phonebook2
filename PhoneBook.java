import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xubt on 4/23/16.
 */
public class PhoneBook {
    private List<Person> persons = new ArrayList<Person>();

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println("成功加载MySQL驱动程序");
        String sql;
        String url = "jdbc:mysql://localhost/db?useUnicode=true" +
                "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=true";
        String user = "root";
        String password = "199011081108";
        return DriverManager.getConnection(url,user,password);
    }

    public void addPerson(Person person) throws Exception {
        if (person == null) {
            throw new Exception("联系人信息不能为空!");
        }
        Connection connection = getConnection();
        String sql;
        sql = " insert into persons(name,phoneNumber) values('"+ person.getName() + "','" + person.getPhoneNumber()+ "')";
        Statement stmt = connection.createStatement();
        int rs = stmt.executeUpdate(sql);
        System.out.println("添加成功!");
    }

    public List<Person> loadPersons() throws SQLException, ClassNotFoundException {
        Connection connection = getConnection();
        String sql;
        sql = "select * from persons";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        List<Person> persons = new ArrayList<Person>();
        while(resultSet.next()) {
            Person person = new Person();
            person.setName(resultSet.getString(1));
            person.setPhoneNumber(resultSet.getString(2));
            persons.add(person);
        }
        return persons;
    }

    public Person findPersonByName(String name) {
        for (Person person : persons) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }

    public Person findRemovePersonByName(String name) {
        for (Person person : persons) {
            if (person.getName().equals(name)) {
                persons.remove(person);
                return person;
            }
        }
        return null;
    }
    public Person EditPersonByName(String name) {
        for (Person person : persons) {
            if (person.getName().equals(name)) {
                persons.remove(persons.indexOf(person));
                return person;
            }
        }
        return null;
    }
}

