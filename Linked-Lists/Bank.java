public class Bank{
    private LinkedList<Account> Accounts;
    public class Account{
        private String name;
        private String address;
        private int SSN;
        private int ID;
        private double balance;

        //Constructors
        Account(String name, String address, int SSN, double balance){
            this.name = name;
            this.address = address;
            this.SSN = SSN;
            this.ID = getLastID() + 1;
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

        public int getID(){
            return this.ID;
        }

        public double getBalance(){
            return this.balance;
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
        this.Accounts = new LinkedList();
    }

    //Getter Methods
    private int getLastID(){
        if (this.Accounts.getHead() == null)
            return 0;
        else
            return this.Accounts.getLastNode().getData().getID();
    }

    //Setter Methods
    public void addUser(String name, String address, int SSN){
        this.Accounts.addNode(new Account(name, address, SSN));
    }

    public void addUser(Account user){
        this.Accounts.addNode((user));
    }
}