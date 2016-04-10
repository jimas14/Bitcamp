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
		String[] str = getPart("./com/bigfu/bitcamp/texts/part1.txt");
		String[] str1 = getPart("./com/bigfu/bitcamp/texts/part2.txt");
		String[] connect1 = {"! ",", ",". ","\n"};
		String[] str2 = getPart("./com/bigfu/bitcamp/texts/part3.txt");

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