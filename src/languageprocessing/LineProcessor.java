/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package languageprocessing;

/*
 * Copyright 2016: smanna@cpp.edu
 * Please do not change any public method's header.
 * Feel free to include your own methods/variables as required.
 */
import java.util.HashSet;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineProcessor {
    
    
    public LineProcessor() {
        // Constructor
        // TODO(student): Feel free to add your initialization code, if any.
    }

    private HashSet<String> findEmails(String line) {
        // TODO(student): Write your regular expression based email
        // extractor code here. Feel free to add as many as private method
        // you need.
        HashSet<String> email = new HashSet();
        
        //name 'at' domain 'dot' extention
        String pattern = 
                "([A-Za-z0-9+_.-]+)\\s*"
                + "(?<!@|[^\\w|\\d]at[^\\w|\\d])"
                + "(@|[^\\w|\\d]at[^\\w|\\d])"
                + "(?!@|[^\\w|\\d]at[^\\w|\\d])\\s*"
                + "([a-zA-Z0-9+]+)\\s*"
                + "((?<!\\.|[^\\w|\\d]dot[^\\w|\\d])"
                + "(\\.|[^\\w|\\d]dot[^\\w|\\d])"
                + "(?!\\.|[^\\w|\\d]dot[^\\w|\\d])\\s*"
                + "([a-z]{2,8}))\\s*"
                + "((?<!\\.|[^\\w|\\d]dot[^\\w|\\d])"
                + "(\\.|[^\\w|\\d]dot[^\\w|\\d])"
                + "(?!\\.|[^\\w|\\d]dot[^\\w|\\d])\\s*"
                + "([a-z]{2,8}))?\\s*"
                + "((?<!\\.|[^\\w|\\d]dot[^\\w|\\d])"
                + "(\\.|[^\\w|\\d]dot[^\\w|\\d])"
                + "(?!\\.|[^\\w|\\d]dot[^\\w|\\d])\\s*"
                + "([a-z]{2,8}))?";

        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(line);

        while (m.find()) {
            if(m.group(10) != null){
                email.add(m.group(1) + '@' + m.group(3) + '.' + m.group(6) + '.' + m.group(9) + '.' + m.group(12));
            } else if(m.group(7) != null){
                email.add(m.group(1) + '@' + m.group(3) + '.' + m.group(6) + '.' + m.group(9));
            }else {
                email.add(m.group(1) + '@' + m.group(3) + '.' + m.group(6));
            }
            
        }
        return email;  // returning empty => no result found
    }

    private HashSet<String> findPhoneNumbers(final String line) {
        // TODO(student): Write your regular expression based phone
        // number extractor code here. Feel free to add as many as private method
        // you need.
        HashSet<String> phoneNumber = new HashSet();
        
        String pattern = 
                  "[^\\d-;/_\\.]+\\s*"
                + "\\(?(\\d{3})\\)?"
                + "[-\\.\\s]*"
                + "(\\d{3})"
                + "[-\\.\\s]*"
                + "(\\d{4})\\b+";

        Pattern r = Pattern.compile(pattern);

        Matcher m = r.matcher(line);

        while (m.find()) {
            phoneNumber.add(m.group(1) + '-' + m.group(2) + '-' + m.group(3));
        }
        return phoneNumber;  // returning empty => no result found
    }

    public HashSet<String> processLine(String line) {
        // You should not be modifying this method.
        HashSet<String> email = findEmails(line);
        HashSet<String> phone = findPhoneNumbers(line);
        HashSet<String> email_n_phones = new HashSet<String>();
        for (String e : email) {
            email_n_phones.add("e\t" + e);
        }
        for (String p : phone) {
            email_n_phones.add("p\t" + p);
        }
        return email_n_phones;
    }
}
