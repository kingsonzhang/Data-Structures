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
            return String.format("Name: %s\nAddress: %s\nSSN: %s\nBalance: %s", this.name, this.address, this.SSN, this.balance);
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
    public Integer getHeadIndex(){
        return this.Accounts.getHeadIndex();
    }

    public int getMedianIndex(){
        return this.Accounts.findMedianIndex();
    }

    public void emptyAccounts(){
        this.Accounts.findEmptyIndexes().print();
    }

    public boolean isEmpty(){
        return this.Accounts.isEmpty();
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

    public void addUser(Account user, int index){
        this.Accounts.addNodeAtIndex(user, index);
    }

    //delete removes and account from the LinkedList of accounts
    //Similar to addUser, functionatly is handled by LinkedList, only pass ID of the account
    public void deleteUser(int ID){
        this.Accounts.deleteNode(ID);
    }

    public Account deleteFirstUser(){
        return Accounts.deleteFirstNode();
    }

    //Finds two accounts from the LinkedList of accounts, removes amount from first account, adds amount to second account
    //Does nothing if either account is not found or does not exist
    //LinkedList handles finding of account by ID
    //Accounts are passed back to Bank, and withdraw and deposit handled by Account class
    public void payToUser(int payerID, int payeeID, double amount){
        Account payer = this.Accounts.findData(payerID);
        Account payee = this.Accounts.findData(payeeID);
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
        Account first = this.Accounts.findData(smallerID);
        Account second = this.Accounts.findData(largerID);
        if (first.getName().equals(second.getName()) && first.getAddress().equals(second.getAddress()) &&  first.getSSN() == second.getSSN()){
            first.deposit(second.getBalance());
            this.Accounts.deleteNode(largerID);
        }
    }

    public Bank mergeBanks(Bank bankOne, Bank bankTwo){
        LinkedList<Account> merged = new LinkedList<>();
        LinkedList<Account> conflicted = new LinkedList<>();
        Integer bankOneFirstIndex = bankOne.getHeadIndex();
        Integer bankTwoFirstIndex = bankTwo.getHeadIndex();
        while (bankOneFirstIndex != null || bankTwoFirstIndex != null){
            if (bankOneFirstIndex == null){
                while (bankTwoFirstIndex != null){
                    merged.addNodeAtIndex(bankTwo.deleteFirstUser(), bankTwoFirstIndex);
                    bankTwoFirstIndex = bankTwo.getHeadIndex();
                }
            }
            else if (bankTwoFirstIndex == null){
                while (bankOneFirstIndex != null){
                    merged.addNodeAtIndex(bankOne.deleteFirstUser(), bankOneFirstIndex);
                    bankOneFirstIndex = bankOne.getHeadIndex();
                }
            }
            else{
                if (bankOneFirstIndex.compareTo(bankTwoFirstIndex) == 0){
                    merged.addNodeAtIndex(bankOne.deleteFirstUser(), bankOneFirstIndex);
                    conflicted.addNode(bankTwo.deleteFirstUser());
                    bankOneFirstIndex = bankOne.getHeadIndex();
                    bankTwoFirstIndex = bankTwo.getHeadIndex();
                }
                else if (bankOneFirstIndex.compareTo(bankTwoFirstIndex) < 0){
                    merged.addNodeAtIndex(bankOne.deleteFirstUser(), bankOneFirstIndex);
                    bankOneFirstIndex = bankOne.getHeadIndex();
                }
                else{
                    merged.addNodeAtIndex(bankTwo.deleteFirstUser(), bankTwoFirstIndex);
                    bankTwoFirstIndex = bankTwo.getHeadIndex();
                }
            }
        }
        Bank combinedBank = new Bank();
        while(!merged.isEmpty()){
            int index = merged.getHeadIndex().intValue();
            combinedBank.addUser(merged.deleteFirstNode(), index);
        }
        while(!conflicted.isEmpty())
            combinedBank.addUser(conflicted.deleteFirstNode());
        return combinedBank;
    }
}