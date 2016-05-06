import java.util.List;
import java.util.Scanner;

/**
 * Created by xubt on 4/23/16.
 */
public class Application {
    private static PhoneBook phoneBook = new PhoneBook();

    public static void main(String[] args) throws Exception {
        backToHome();
    }

    public static void backToHome() throws Exception {
        System.out.println("欢迎!");
        System.out.println("选择要进行的操作:");
        System.out.println("1,添加联系人");
        System.out.println("2,查找联系人");
        System.out.println("3,删除联系人");
        System.out.println("4,修改联系人");
        System.out.println("5,查看所有联系人");
        Scanner input = new Scanner(System.in);
        int selectedOption = input.nextInt();
        switch (selectedOption) {
            case 1:
                System.out.println("请输入联系人的姓名:");
                Person newPerson = new Person();
                Scanner scanner = new Scanner(System.in);
                newPerson.setName(scanner.nextLine());
                System.out.println("请输入联系人的号码:");
                newPerson.setPhoneNumber(scanner.nextLine());
                phoneBook.addPerson(newPerson);
                System.out.println(newPerson.getName() + "已经添加到电话本!");
                break;
            case 2:
                System.out.println("请输入查找的联系人");
                input = new Scanner(System.in);
                Person foundPerson = phoneBook.findPersonByName(input.nextLine());
                if (foundPerson == null) {
                    System.out.println("未找到联系人");
                }
                else {
                    System.out.print(foundPerson.getName() + ":" + foundPerson.getPhoneNumber());
                }
                break;
            case 3:
                System.out.println("请输入要删除的联系人");
                input = new Scanner(System.in);
                Person foundRemovePerson = phoneBook.findRemovePersonByName(input.nextLine());
                if (foundRemovePerson == null) {
                    System.out.println("删除的联系人不存在！");
                }
                else {
                    System.out.println("删除成功！");
                    break;
                }
            case 4:
                System.out.println("请输入要修改的联系人");
                input = new Scanner(System.in);
                Person foundEditPerson = phoneBook.EditPersonByName(input.nextLine());
                if (foundEditPerson == null){
                    System.out.println("修改的联系人不存在");
                }
                else{
                    System.out.println("修改成功！");
                    break;
                }
            case 5:
                List<Person> persons = phoneBook.loadPersons();
                for (Person person : persons) {
                    System.out.println("姓名:" + person.getName());
                    System.out.println("号码:" + person.getPhoneNumber());
                    System.out.println("----------------------------------");
                }
        }
        backToHome();
    }
}
