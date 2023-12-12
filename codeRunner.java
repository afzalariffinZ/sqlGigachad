public class codeRunner {



    //This is where codes are being run
    public static void main(String[] args) {
        //creating instance of Prompt, Database and basicUI class
        Prompt newPrompt = new Prompt(); 
        register checker = new register(); 
        login Login = new login();

        while(true){
            basicUI base = new basicUI();
            //calling the UI
            int userChoice = base.ui();

            if( userChoice == 1){   //register
                String[] newData = new String[3]; //creating an array to fill user's email, username and password
                newPrompt.promptRegister(newData); // calling the prompter method from Prompt.java using newPrompt instance to ask user for their infos
                /*
                * newData[0] = email
                * newData[1] = username
                * newData[2] = password
                */
                checker.sqlConnectorRegister(newData[0], newData[1], newData[2]); //checking and inserting user info into the SQL Database
            }else if(userChoice == 2){ //login
                String[] newData = new String[3];
                newPrompt.promptLogin(newData);
                Login.sqlConnectorLogin(newData[0], newData[1], newData[2]);

            }else if(userChoice == 3){ //exiting
                System.exit(0);
            }else{
                System.out.println("Please insert the input properly!");
            }

        }
        

    }
}
