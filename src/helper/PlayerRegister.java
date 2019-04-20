package helper;

import constants.ApplicationConstants;
import model.Player;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class PlayerRegister {
    public void registerPlayer(Player player) {
        File playerFile = new File(ApplicationConstants.APP_FOLDER_DATA_PATH +
                ApplicationConstants.PATH_SEPARATOR +
                ApplicationConstants.PLAYERS_FOLDER_NAME +
                ApplicationConstants.PATH_SEPARATOR +
                player.getName());

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Player.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(player, playerFile);
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public Player readPlayer(String playerName) {
        Player player = null;

        File playerFile = new File(ApplicationConstants.APP_FOLDER_DATA_PATH +
                ApplicationConstants.PATH_SEPARATOR +
                ApplicationConstants.PLAYERS_FOLDER_NAME +
                ApplicationConstants.PATH_SEPARATOR +
                playerName);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Player.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            player = jaxbUnmarshaller.unmarshal(playerFile);
        } catch (JAXBException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return player;
    }
}
