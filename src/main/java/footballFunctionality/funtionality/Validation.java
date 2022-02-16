package footballFunctionality.funtionality;

import footballFunctionality.model.PlayerInfo;

import java.util.regex.Pattern;

/**
 * For validating the fields
 */
public class Validation
{
    /**
     * Returns 'true' if the validations are passed.
     * @param player
     * @return
     */
    public static boolean isValid(PlayerInfo player)
    {
        System.out.println("validations start");
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

        if(player.getPinCode().length() > 0) {
            if(!(Pattern.matches("[0-9]{6}", player.getPinCode()))){
                return false;
            }
        }
        if(Integer.parseInt(player.getCountry()) < 1) return false;
        if(Integer.parseInt(player.getState()) < 1) return false;
        if(Integer.parseInt(player.getCity()) < 1) return false;
        System.out.println("validations done");
        return true;
    }
}
