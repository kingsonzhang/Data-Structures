public class Tester{
    public static void main(String args[]){
        Bank bankOfOrangeCounty = new Bank();
        Bank bankOfLosAngeles = new Bank();

        bankOfOrangeCounty.addUser("Mickey Mouse", "Disneyland", 92802, 2000000000);
        bankOfOrangeCounty.addUser("Snoopy", "Knotts", 90620, 500000000);
        bankOfOrangeCounty.addUser("Juan Capistrano", "Mission", 92675, 250000);
        bankOfOrangeCounty.addUser("Heisler Park", "Laguna", 92651, 500000);
        bankOfOrangeCounty.addUser("Crystal Cove", "Laguna", 92651, 100000);
        //bankOfOrangeCounty.print();

        bankOfLosAngeles.addUser("John Smith", "Arcadia", 90601, 50000);
        bankOfLosAngeles.addUser("Jane Doe", "Azusa", 90605, 60000);
        bankOfLosAngeles.addUser("James Bond", "Claremont", 90213, 80000);
        bankOfLosAngeles.addUser("Helen Park", "Lakewood", 90265, 75000);
        bankOfLosAngeles.addUser("Frank Thomas", "Pasadena", 90301, 125000);
        bankOfLosAngeles.deleteUser(4);
        bankOfLosAngeles.payToUser(1, 2, 10000);
        //bankOfLosAngeles.print();
        //System.out.println(bankOfLosAngeles.getMedianID());

        bankOfOrangeCounty.mergeBanks(bankOfLosAngeles);
        bankOfOrangeCounty.print();
        bankOfLosAngeles.print();
    }
}