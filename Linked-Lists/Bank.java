public class Bank{
    private LinkedList<Account> Accounts;
    public class Account{
        private String name;
        private String address;
        private int SSN;
        private double balance;

        //Constructors
        Account(String name, String address, int SSN, double balance){
            this.name = name;
            this.address = address;
            this.SSN = SSN;
            this.balance = balance;
        }

        Account(String name, String address, int SSN){
            this(name, address, SSN, 0.0);
        }

        //Getter Methods
        public String getName(){
            return this.name;
        }

        public String getAddress(){
            return this.address;
        }

        public int getSSN(){
            return this.SSN;
        }

        public double getBalance(){
            return this.balance;
        }

        public String toString(){
            return String.format("Name: %s\nAddress: %s\nSSN: %s\nBalance: %s\n", this.name, this.address, this.SSN, this.balance);
        }

        //Setter Methods
        public void setName(String name){
            this.name = name;
        }

        public void setAddress(String address){
            this.address = address;
        }

        public void setSSN(int SSN){
            this.SSN = SSN;
        }

        public void setBalance(double balance){
            this.balance = balance;
        }
    }

    //Constructors
    Bank(){
        this.Accounts = new LinkedList<Account>();
    }

    //Getter Methods

    //For testing purposes only
    public void print(){
        if (Accounts.isEmpty())
            System.out.println("No bank accounts");
        else{
            this.Accounts.print();
        }
    }

    //Setter Methods
    public void addUser(String name, String address, int SSN){
        this.Accounts.addNode(new Account(name, address, SSN));
    }

    public void addUser(Account user){
        this.Accounts.addNode((user));
    }

    public void deleteUser(int ID){
        this.Accounts.deleteNode(ID);
    }
}