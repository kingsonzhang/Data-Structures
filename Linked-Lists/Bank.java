public class Bank{
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

        /**
         * Increases user balance by given amount
         * @param amount to deposit into user account
         */
        public void deposit(double amount){
            this.balance += amount;
        }
        
        /**
         * Decreases user balance by given amount
         * @param amount to withdraw into user account
         */
        public void withdraw(double amount){
            this.balance -= amount;
        }
    }

    //Constructors
    Bank(){
        this.Accounts = new LinkedList<>();
    }

    //Getter Methods
    public Integer getHeadIndex(){
        return this.Accounts.getHeadIndex();
    }

    /**
     * Return the median index value of all of the accounts in the Bank
     */
    public int getMedianIndex(){
        return this.Accounts.findMedianIndex();
    }

    /**
     * Prints the empty indexes in the Bank
     */
    public void emptyAccounts(){
        this.Accounts.findEmptyIndexes().print();
    }

    /**
     * Returns if the Bank has no accounts
     */
    public boolean isEmpty(){
        return this.Accounts.isEmpty();
    }

    public void print(){
        if (Accounts.isEmpty())
            System.out.println("No bank accounts");
        else{
            this.Accounts.print();
        }
    }

    //Setter Methods
    /**
     * Creates a new Account with the specified user information and adds it to the list of accounts.
     * @param name     the user's name
     * @param address  the user's address
     * @param SSN      the user's Social Security Number
     * @param balance  the user's initial account balance
     */
    public void addUser(String name, String address, int SSN, double balance){
        this.Accounts.addNode(new Account(name, address, SSN, balance));
    }

    /**
     * Creates a new Account with the specified user information and adds it to the list of accounts.
     * With no given initial bank balance, balance is default set to 0
     * @param name     the user's name
     * @param address  the user's address
     * @param SSN      the user's Social Security Number
     */
    public void addUser(String name, String address, int SSN){
        this.addUser(name, address, SSN, 0.0);
    }

    /**
     * Adds a user into the Bank
     * @param user User information wrapped into a Bank class Object
     */
    public void addUser(Account user){
        this.Accounts.addNode((user));
    }

    /**
     * Adds a user into the Bank at the specified index
     * @param user User information wrapped into a Bank class Object
     * @param index Given index to insert user into the bank
     */
    public void addUser(Account user, int index){
        this.Accounts.addNodeAtIndex(user, index);
    }

    /**
     * Deletes a user from the Bank
     * @param ID Index of the user to be deleted
     */
    public void deleteUser(int ID){
        this.Accounts.deleteNode(ID);
    }

    /**
     * Deletes the first Account from the Bank
     */
    public Account deleteFirstUser(){
        return Accounts.deleteFirstNode();
    }

    /**
     * Transfer money from payer Account into Payee account
     * @param payerID Index of the Payer
     * @param payeeID Index of the Payee
     * @param amount Amount to be transfered between the accounts
     */
    public void payToUser(int payerID, int payeeID, double amount){
        Account payer = this.Accounts.findData(payerID);
        Account payee = this.Accounts.findData(payeeID);
        if (payer != null && payee != null){
            payer.withdraw(amount);
            payee.deposit(amount);
        }
    }

    /**
     * Finds the merges two Accounts into one if given IDs of both accounts are found
     * First index of the two Account stays, the balance is moved into the first Account
     * The second Account is deleted from the Bank
     * @param firstID One of the given IDs to search for. Order does not matter
     * @param secondID Second of the given IDs to search for. Order does not matter
     */
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

    /**
     * Merges the accounts from two Bank instances into a single new Bank
     * @param bankOne the first bank whose accounts will be merged
     * @param bankTwo the second bank whose accounts will be merged
     * @return a new Bank object containing the merged list of accounts
     */
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
            int index = merged.getHeadIndex();
            combinedBank.addUser(merged.deleteFirstNode(), index);
        }
        while(!conflicted.isEmpty())
            combinedBank.addUser(conflicted.deleteFirstNode());
        return combinedBank;
    }
}