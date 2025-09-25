public class Tester{
    public static void main(String args[]){
        Bank OrangeCountyBank = new Bank();
        OrangeCountyBank.print();
        OrangeCountyBank.addUser("Tom", "123 Lane", 1);
        OrangeCountyBank.addUser("Jane", "125 Lane", 5);
        OrangeCountyBank.addUser("Leo", "127 Lane", 11);
        OrangeCountyBank.print();
        OrangeCountyBank.deleteUser(2);
        OrangeCountyBank.print();
        OrangeCountyBank.addUser("Sarah", "129 Lane", 50);
        OrangeCountyBank.print();
    }
}