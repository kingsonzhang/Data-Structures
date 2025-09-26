public class Tester{
    public static void main(String args[]){
        Bank OrangeCountyBank = new Bank();
        OrangeCountyBank.print();
        OrangeCountyBank.addUser("Tom", "123 Lane", 123456789, 100.00);
        OrangeCountyBank.addUser("Jane", "125 Lane", 987654321, 0.0);
        OrangeCountyBank.addUser("Leo", "127 Lane", 123459876, 50.00);
        OrangeCountyBank.print();
        OrangeCountyBank.deleteUser(2);
        OrangeCountyBank.print();
        OrangeCountyBank.addUser("Sarah", "129 Lane", 987612345);
        OrangeCountyBank.print();
        OrangeCountyBank.payToUser(1, 3, 25.00);
        OrangeCountyBank.print();
    }
}