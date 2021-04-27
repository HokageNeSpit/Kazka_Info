package kazka.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataBase {
    public ArrayList<FairyTale> fairyTales = new ArrayList<>();
    public ArrayList<FairyTale> listOfUserLike = new ArrayList<>();
    public boolean isAdmin;
    public boolean info;

    public DataBase() {
        fairyTales.add(new FairyTale(randomRate(), "Cinderella", "\"Cinderella\" is one example of classic fairy tales for kids. Once there was a hardworking girl with a heart of gold and a wicked stepmother. She got a makeover from a fairy godmother and scored a dream date at the ball with a prince who tracked her down by her single lost glass slipper... and this story crossed the globe for thousands of years, winning hearts wherever it went. Although our version of \"Cinderella\" was recorded by 17th-century French writer Charles Perrault, according to the well-respected scholarly website Sur La Lune Fairy Tales, there may be as many as 1,500 traditional variants of the tale around the world, including \"The Girl with the Rose Red Slippers\" from ancient Egypt, and a ninth-century A.D. Chinese version that just might explain the story's fascination with small feet."));
        fairyTales.add(new FairyTale(randomRate(), "Beauty and the Beast", "No plot could be more romantic than this one: A kind and virtuous Beauty offers herself as a hostage to free her father from the castle of a fearsome Beast. When she falls in love with the Beast despite his outward appearance, he's transformed into a handsome Prince. Who among us has not felt unworthy of a lover yet longed to have our inner value recognized? Who has not dreamed of romantic love with the power to redeem and transform? No wonder \"Beauty and the Beast,\" originally a French story, is the second most frequently visited fairy tales for kids on Sur La Lune Fairy Tales."));
        fairyTales.add(new FairyTale(randomRate(), "Little Red Riding Hood", "Though the story was probably intended as a warning for children to follow directions, the rebellious character of Little Red Riding Hood is the most modern of the fairy tale heroines we've met so far. In this fairy tale for kids, Red sets off alone to visit her grandmother with instructions not to step off the forest path—advice she promptly disregards, attracting the attention of a talking wolf who sets out to eat and impersonate Grandma. What happens next depends on what you read. In the 17th-century French version recorded by Charles Perrault, Red gets gobbled up by the wolf. The End. In other tellings, across Europe, North America, China, Japan, and Ghana, she's saved at the last minute by a guy with an axe, or the wolf chokes on her hood, or he eats both Grandma and Red but is forced to vomit them up unharmed."));
        fairyTales.add(new FairyTale(randomRate(), "Snow White and the Seven Dwarfs", "Snow White is more of a patsy than many of these fairy tale heroines (which is saying quite a bit). The most active thing she does is mother a household full of dwarfs. She never retaliates against the evil queen who tries to kill her for her youth and beauty, she waits for her prince frozen in her glass coffin, as feminist critics have put it, \"an object, to be displayed and desired... patriarchy's ideal woman, the perfect candidate for Queen.\" In an essay entitled \"My Stepmother, Myself,\" Garrison Keillor actually suspects the prince of necrophilia! Yikes! That doesn't sound like one of the best fairy tales for kids."));
        isAdmin = false;
        info = false;
    }

    public void addFairyTale(int rate, String name, String description) {
        fairyTales.add(new FairyTale(rate, name, description));
    }

    public void removeFairyTale(int id) {
        fairyTales.remove(fairyTales.get(id));
    }

    public int randomRate() {
        Random random = new Random();
        return random.nextInt(100 + 1);
    }

    public FairyTale findById(int id) {
        return fairyTales.get(id);
    }

    public void replace(int id, String nameOfFairyTale, String description) {
        fairyTales.set(id, new FairyTale(findById(id).rate, nameOfFairyTale, description));
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void increaseLike(int id) {
        findById(id).rate++;
    }

    public void setInfo(boolean information) {
        info = information;
    }

    public void addLikedFairyTale(int id) {
        listOfUserLike.add(new FairyTale(fairyTales.get(id).rate, fairyTales.get(id).name, fairyTales.get(id).description));
    }

    public void deleteLikedFairyTale(int id) {
        int idx = 0;
        boolean find = true;
        while (find) {
            if (findById(id).name.equals(listOfUserLike.get(idx).name)) {
                listOfUserLike.remove(listOfUserLike.get(idx));
                find = false;
            }
            idx++;
        }
    }


    public boolean liked(int id) {
        for (FairyTale ft : listOfUserLike) {
            if (fairyTales.get(id).name.equals(ft.name)) {
                return false;
            }
        }
        return true;
    }
}
