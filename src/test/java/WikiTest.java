import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;

public class WikiTest extends BaseForTest{
        private final static String URL = "https://ru.wikipedia.org/wiki/Java";

        @Test
        public void openAllHrefs() {
            Selenide.open(URL);
            ElementsCollection hrefs = $$x("//div[@id='toc']//a[@href]");
            List<String> links = new ArrayList<>();
            //1 способ. Заполняем список с помощью метода for-int
  //          for (int i = 0; i < hrefs.size(); i++){
  //              links.add(hrefs.get(i).getAttribute("href"));
  //          }
            //2 способ. Перебираем тип элемента из коллекции с помощью метода for-each.
            // этот способ удобен тем, что тут не надо указывать индекс
            for(SelenideElement element : hrefs){
                links.add(element.getAttribute("href"));
            }
            //3. Работа со stream-api.
  //          hrefs.forEach(x->links.add(x.getAttribute("href")));

            // перебираем значения из полученных ссылок:

            //1. Открытие всех полученных ссылок с помощью stream-api
  //          links.forEach(Selenide::open);

            //2. Открытие всех ссылок и выаолнение assert
   //                  for(int i=0; i<links.size(); i++){
                //получаем ссылку из списка
  //              String listUrl = links.get(i);
                // открываем эту ссылку
  //              Selenide.open(listUrl);
                //получаем ссылку именно из браузера
  //              String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
                // сравнивваем, нужная ли ссылка открылась
  //              Assertions.assertEquals(currentUrl, listUrl);
  //          }

            //3. Получаем случайное значение из списка с ссылками, достаем их
            // и открывшиеся ссылки убираем из списка
            Random random = new Random();
            while(links.size()>0){
                int randomNumber = random.nextInt(links.size());
                Selenide.open(links.get(randomNumber));
                links.remove(WebDriverRunner.getWebDriver().getCurrentUrl());

            }

            //4. Работа со stream-api
            // получим список со всеми ссылками, но вместо ссылок - длину этих ссылок
            List<Integer> linksLenght = hrefs.stream().map(x->x.getAttribute("href").length()).collect(Collectors.toList());

        }

}
