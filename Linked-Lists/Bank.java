public class Bank{
    //Bank will hold a LinkedList of Accounts
    //Accounts is a class that holds 4 private data and additional methods
    //Data will be user name, user address, user Social Security Number, and bank balance
    private LinkedList<Account> Accounts;
    public class Account{
        private String name;
        private String address;
        private int SSN;
        private double balance;

        //Account Constructors.
        Account(String name, String address, int SSN, double balance){
            this.name = name;
            this.address = address;
            this.SSN = SSN;
            this.balance = balance;
        }

        //Account Constructor without a balance variable defaults to 0 balance
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

        //For testing purposes only
        @Override
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

        //deposit method takes an amount and adds it into the balance of the user
        public void deposit(double amount){
            this.balance += amount;
        }
        
        //withdraw method takes an amount and subtracts it from the balance of the user
        public void withdraw(double amount){
            this.balance -= amount;
        }
    }

    //Constructors
    //By default creates an empty LinkedList of accounts because no accounts exist
    Bank(){
        this.Accounts = new LinkedList<>();
    }

    //Getter Methods
    public int getMedianID(){
        return this.Accounts.findMedianID();
    }

    //For testing purposes only
    public void print(){
        if (Accounts.isEmpty())
            System.out.println("No bank accounts");
        else{
            this.Accounts.print();
        }
    }

    //Setter Methods
    //addUser takes in information and adds a new Account into the LinkedList at the first available ID (empty slot)
    //Functionality of inserting is handled by LinkedList class, only have to pass data along
    public void addUser(String name, String address, int SSN, double balance){
        this.Accounts.addNode(new Account(name, address, SSN, balance));
    }

    //addUser defaults to 0 balance if balance is not given
    public void addUser(String name, String address, int SSN){
        this.addUser(name, address, SSN, 0.0);
    }

    //addUser can also take in a Account variable
    public void addUser(Account user){
        this.Accounts.addNode((user));
    }

    //delete removes and account from the LinkedList of accounts
    //Similar to addUser, functionatly is handled by LinkedList, only pass ID of the account
    public void deleteUser(int ID){
        this.Accounts.deleteNode(ID);
    }

    //Finds two accounts from the LinkedList of accounts, removes amount from first account, adds amount to second account
    //Does nothing if either account is not found or does not exist
    //LinkedList handles finding of account by ID
    //Accounts are passed back to Bank, and withdraw and deposit handled by Account class
    public void payToUser(int payerID, int payeeID, double amount){
        Account payer = this.Accounts.findNode(payerID);
        Account payee = this.Accounts.findNode(payeeID);
        if (payer != null && payee != null){
            payer.withdraw(amount);
            payee.deposit(amount);
        }
    }

    //LinkedList finds two accounts, and if both accounts exist
    //Merge the balance of both accounts into the earlier Account in the LinkedList
    //Delete the later Account in the LinkedList
    public void mergeAccounts(int firstID, int secondID){
        int smallerID = firstID < secondID ? firstID : secondID;
        int largerID = firstID < secondID ? secondID : firstID;
        Account first = this.Accounts.findNode(smallerID);
        Account second = this.Accounts.findNode(largerID);
        if (first.getName().equals(second.getName()) && first.getAddress().equals(second.getAddress()) &&  first.getSSN() == second.getSSN()){
            first.deposit(second.getBalance());
            this.Accounts.deleteNode(largerID);
        }
    }

    //mergeBank is a little poorly written. The parameter Bank is the one that becomes the main merged bank
    //Self Bank loses all Accounts, and parameter Bank will add all Accounts of current Bank
    public void mergeBanks(Bank combinedBank){
        while (!this.Accounts.isEmpty()){
            combinedBank.addUser(this.Accounts.getHead().getData());
            this.Accounts.deleteFirstNode();
        }
    }
}