// Computer Science 311 - Assignment 2: AI Survey Program
// Nicholas Ives
// Study.com
// September 12, 2024

import java.io.*;
import java.util.*;

public class PoliticalPartySurvey {
    private static final int TOTAL_QUESTIONS = 20;
    private static final String[] PARTIES = {"Republican", "Democrat", "Green Party", "Libertarian"};
    private static final int[] GUESS_INTERVALS = {7, 11, 15};
    
    private List<Question> questions;
    private int[] partyScores;
    private List<String> userAnswers;
    private Scanner scanner;
    
    public PoliticalPartySurvey() {
        questions = new ArrayList<>();
        partyScores = new int[PARTIES.length];
        userAnswers = new ArrayList<>();
        scanner = new Scanner(System.in);
        initializeQuestions();
    }
    
    // Initializes the 20 survey questions.
    private void initializeQuestions() {
        questions.add(new Question("What should the government do to help the poor?",
            new String[]{"Make it easier to apply for assistance", "Allow parents to use education funds for charter schools",
                         "Create welfare to work programs", "Nothing"}));
        
        questions.add(new Question("What is your stance on gun control?",
            new String[]{"Stricter gun laws are needed", "Protect the right to bear arms",
                         "Ban assault weapons", "Eliminate all gun control laws"}));
        
        questions.add(new Question("How should healthcare be managed?",
            new String[]{"Universal healthcare for all", "Free market healthcare system",
                         "Public option with private insurance", "Completely privatize healthcare"}));
        
        questions.add(new Question("What is your view on climate change?",
            new String[]{"It's a critical threat requiring immediate action", "It's important but shouldn't harm the economy",
                         "It's the most pressing issue of our time", "It's not a significant concern"}));
        
        questions.add(new Question("What is your stance on abortion?",
            new String[]{"Pro-choice", "Pro-life with some exceptions",
                         "Pro-choice with some restrictions", "Completely pro-life"}));
        
        questions.add(new Question("How should immigration be handled?",
            new String[]{"Create a path to citizenship for undocumented immigrants", "Strengthen border security",
                         "Open borders policy", "Strict enforcement of current laws"}));
        
        questions.add(new Question("What is your view on taxation?",
            new String[]{"Increase taxes on the wealthy", "Lower taxes across the board",
                         "Progressive tax system with high rates for top earners", "Flat tax rate for all"}));
        
        questions.add(new Question("How should education be funded?",
            new String[]{"Increase federal funding for public schools", "School choice and vouchers",
                         "Free college education for all", "Privatize education system"}));
        
        questions.add(new Question("What is your stance on minimum wage?",
            new String[]{"Raise the federal minimum wage", "Let states decide minimum wage",
                         "Implement a living wage", "Abolish the minimum wage"}));
        
        questions.add(new Question("How should the criminal justice system be reformed?",
            new String[]{"Focus on rehabilitation and reducing incarceration", "Tougher sentences for violent crimes",
                         "Decriminalize non-violent offenses", "Privatize prisons"}));
        
        questions.add(new Question("What is your view on foreign policy?",
            new String[]{"Strengthen international alliances", "America First approach",
                         "Focus on global cooperation and peace", "Non-interventionism"}));
        
        questions.add(new Question("How should energy policy be approached?",
            new String[]{"Invest in renewable energy", "Support fossil fuels and energy independence",
                         "Rapid transition to 100% renewable energy", "Let the free market decide"}));
        
        questions.add(new Question("What is your stance on corporate regulation?",
            new String[]{"Increase regulation to protect consumers", "Reduce regulation to promote business growth",
                         "Break up large corporations", "Eliminate most corporate regulations"}));
        
        questions.add(new Question("How should social programs be managed?",
            new String[]{"Expand social safety net", "Reform and reduce welfare programs",
                         "Universal basic income", "Eliminate most federal social programs"}));
        
        questions.add(new Question("What is your view on LGBTQ+ rights?",
            new String[]{"Support full equality and anti-discrimination laws", "Protect religious freedom",
                         "Actively promote LGBTQ+ rights and representation", "Government should not be involved in personal matters"}));
        
        questions.add(new Question("How should drug policy be approached?",
            new String[]{"Decriminalize drug use and focus on treatment", "Tougher sentences for drug offenses",
                         "Legalize and regulate all drugs", "Let states decide drug policy"}));
        
        questions.add(new Question("What is your stance on affirmative action?",
            new String[]{"Support affirmative action policies", "Merit-based system only",
                         "Expand affirmative action to more groups", "Eliminate all affirmative action programs"}));
        
        questions.add(new Question("How should the national debt be addressed?",
            new String[]{"Increase taxes on the wealthy to pay down debt", "Cut government spending across the board",
                         "Implement wealth tax and redirect military spending", "Drastically reduce government size and spending"}));
        
        questions.add(new Question("What is your view on unions and labor laws?",
            new String[]{"Strengthen unions and worker protections", "Reduce union influence and labor regulations",
                         "Mandate worker representation on corporate boards", "Eliminate most labor laws and unions"}));
        
        questions.add(new Question("How should voting rights be managed?",
            new String[]{"Expand voting access and registration", "Implement stricter voter ID laws",
                         "Automatic voter registration for all citizens", "Let states manage their own voting laws"}));
    }
    
    // Present the survey to the user, guessing the party affiliation after certain intervals.
    public void conductSurvey() {
        System.out.println("Welcome to the Political Party Affiliation Survey!");
        
        for (int i = 0; i < TOTAL_QUESTIONS; i++) {
            Question question = questions.get(i);
            System.out.println("\nQuestion " + (i + 1) + ": " + question.getText());
            for (int j = 0; j < question.getOptions().length; j++) {
                System.out.println((char)('A' + j) + ". " + question.getOptions()[j]);
            }
            
            String answer = getValidAnswer();
            userAnswers.add(answer);
            updatePartyScores(answer);
            
            if (Arrays.binarySearch(GUESS_INTERVALS, i + 1) >= 0) {
                makeGuess();
            }
        }
        
        String finalGuess = getFinalGuess();
        String affiliatedParty = getFinalPartyAffiliation();
        saveResults(affiliatedParty, finalGuess);
        
        System.out.println("\nThank you for completing the survey!");
    }
    
    // Gets a valid answer from the user, if not a valid answer, reprompt.
    private String getValidAnswer() {
        while (true) {
            System.out.print("Your answer (A/B/C/D): ");
            String answer = scanner.nextLine().toUpperCase();
            if (answer.matches("[A-D]")) {
                return answer;
            }
            System.out.println("Invalid input. Please enter A, B, C, or D.");
        }
    }
    
    // This will update the scores for each party affiliation depending on the user's answers. A generally aligns with Democrat, B Republican, C Green Party, and D Libertarian. Added some leeway.
    private void updatePartyScores(String answer) {
        switch (answer) {
            case "A":
                partyScores[1] += 2; // Democrat
                partyScores[2] += 1; // Green Party
                break;
            case "B":
                partyScores[0] += 2; // Republican
                partyScores[3] += 1; // Libertarian
                break;
            case "C":
                partyScores[2] += 2; // Green Party
                partyScores[1] += 1; // Democrat
                break;
            case "D":
                partyScores[3] += 2; // Libertarian
                partyScores[0] += 1; // Republican
                break;
        }
    }
    
    private void makeGuess() {
        String guess = getFinalGuess();
        System.out.println("\nBased on your answers so far, you might be affiliated with the " + guess + " party.");
    }

    // Uses the scores to guess the user's affiliation based on their answers.
    private String getFinalGuess() {
        int maxScore = partyScores[0];
        int maxIndex = 0;
        for (int i = 1; i < partyScores.length; i++) {
            if (partyScores[i] > maxScore) {
                maxScore = partyScores[i];
                maxIndex = i;
            }
        }
        return PARTIES[maxIndex];
    }
    
    // Prompt the user to input which party they actually affiliate with.
    private String getFinalPartyAffiliation() {
        System.out.println("\nFinal question: Which political party do you affiliate with?");
        for (int i = 0; i < PARTIES.length; i++) {
            System.out.println((i + 1) + ". " + PARTIES[i]);
        }
        
        while (true) {
            System.out.print("Enter the number of your affiliated party: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= PARTIES.length) {
                    return PARTIES[choice - 1];
                }
            } catch (NumberFormatException e) {
                // Ignore and re-prompt
            }
            System.out.println("Invalid input. Please enter a number between 1 and " + PARTIES.length + ".");
        }
    }
    
    // Saves the survey results in a .txt file.
    private void saveResults(String affiliatedParty, String finalGuess) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("survey_results.txt"))) {
            writer.println("Political Party Affiliation Survey Results");
            writer.println("Affiliated Party (User's Statement): " + affiliatedParty);
            writer.println("Final Guess (Program's Prediction): " + finalGuess);
            writer.println("\nQuestions and Answers:");
            for (int i = 0; i < TOTAL_QUESTIONS; i++) {
                writer.println("Q" + (i + 1) + ": " + questions.get(i).getText());
                writer.println("A: " + userAnswers.get(i));
            }
        } catch (IOException e) {
            System.out.println("Error saving results to file: " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        PoliticalPartySurvey survey = new PoliticalPartySurvey();
        survey.conductSurvey();
    }
}

class Question {
    private String text;
    private String[] options;
    
    public Question(String text, String[] options) {
        this.text = text;
        this.options = options;
    }
    
    public String getText() {
        return text;
    }
    
    public String[] getOptions() {
        return options;
    }
}