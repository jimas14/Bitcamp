package llc.bigfu.bigfucamp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;

/*
	Created by Jimmy on 4/9/16
*/
public class RandomText {
	public static int getFileLength(String filename){
		int lineNum = 0;

		try{
			BufferedReader br = new BufferedReader(new FileReader(filename));
			while (br.readLine() != null) 
				lineNum++;
			br.close();
		}catch(Exception e){
			e.printStackTrace();
		}

		return lineNum;
	}

	public static String[] getPart(String filename){
		String[] texts = new String[getFileLength(filename)];

		try{
			BufferedReader br = new BufferedReader(new FileReader(filename));

			//Adds strings to an array
			String line = "";
			int i = 0;

			while((line = br.readLine()) != null){
				texts[i] = new String(line);
				i++;
			}

			br.close();

		}catch(Exception e){
			e.printStackTrace();
		}

		return texts;
	}
	
	public static String getRandomText(){
		String[] str = {"Good morning",
						"Hello",
						"Hey",
						"Heyy",
						"Hay",
						"Morning",
						"Mornin'",
						"Bonjour",
						"Buenos Dias",
						"Konnichiwa",
						"Hi there",
						"Namaste",
						"Greetings",
						"Sup",
						"Whats up"};
		String[] str1 = {"honey",
						"sweet",
						"sweetie",
						"sweetcheeks",
						"beautiful",
						"perfect",
						"angel",
						"honeybun",
						"adorable",
						"baby cakes",
						"baby girl",
						"cuddlebug",
						"honey bunny",
						"cutie patootie",
						"gorgeous",
						"hotness",
						"lover",
						"pancake",
						"princess",
						"pudding",
						"soul mate",
						"sugar",
						"sunshine",
						"sweetheart",
						"honey muffins",
						"snuggle muffins"};
		String[] connect1 = {"! ",", ",". ","\n"};
		String[] str2 = {"I love you.",
						"Hope you have a good day!",
						"Guess who's my favorite person?",
						"You're the most amazing thing that has happened to me.",
						"Your eyes are from heaven.",
						"Every day this world benefits from your love.",
						"Your heart is a force for good.",
						"Your mind and body are the true definition of beauty.",
						"I like you.",
						"The sunshine illuminates your beautiful soul.",
						"Where have you been all my life?",
						"God has blessed this world with another day of your smile"};

		Random randomGenerator = new Random();
		randomGenerator.setSeed(System.currentTimeMillis()*randomGenerator.nextInt(5746));
		int rand1 = randomGenerator.nextInt(str.length);
		int rand2 = randomGenerator.nextInt(str1.length);
		int randc1 = randomGenerator.nextInt(connect1.length);
		int rand3 = randomGenerator.nextInt(str2.length);

		return (str[rand1] + " " + str1[rand2] + connect1[randc1] + str2[rand3]);
	}
	

	public static void main (String args[]){
		System.out.println(RandomText.getRandomText());
	}
}