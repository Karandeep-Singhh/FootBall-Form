package servlet;

import java.util.regex.Pattern;

public class Validation {

    public boolean isValid(Player player) {
//        boolean error = true;

        if (!Pattern.matches("(\\w+)", player.getUserName())) return false;
        if(!Pattern.matches("^([a-zA-Z]{1,})$", player.getFirstName())) return false;
        if(player.getLastName().length() > 0 && !(Pattern.matches("[a-zA-Z\\s]+",player.getLastName()))) return false;
        if(Integer.parseInt(player.getPhoneCode()) < 1) return false;
        if(!(Pattern.matches("[0-9]{10}",player.getPhoneNumber()))) return false;
        if(!(Pattern.matches("^[A-Za-z0-9_\\-\\.]+\\@(\\[?)[\\w\\-\\.]+\\.([a-zA-Z]{2,8}|[0-9]{1,3})(\\]?)\\;{0,}$",player.getEmail()))) return false;
        if(Integer.parseInt(player.getAgeGroup()) < 1) return false;
        if(Integer.parseInt(player.getTeam()) < 1) return false;
        if(Integer.parseInt(player.getPosition()) < 1) return false;
        if(Pattern.matches("[@/=<>$]",player.getAddress())) return false;
        if(!(Pattern.matches("[0-9]")))
        return true;
    }
}
