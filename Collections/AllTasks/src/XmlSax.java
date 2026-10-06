import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;

class XmlSaxTask {

    public static void main(String[] args) {

        int filterYear = 1900;

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            File file = new File("books.xml");

            BookHandler handler = new BookHandler(filterYear);

            parser.parse(file, handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class BookHandler extends DefaultHandler {

    private int filterYear;

    private String currentElement;

    private String title;
    private String author;
    private int year;

    public BookHandler(int filterYear) {
        this.filterYear = filterYear;
    }

    @Override
    public void startElement(
            String uri,
            String localName,
            String qName,
            Attributes attributes
    ) throws SAXException {

        currentElement = qName;

        if (qName.equals("book")) {
            title = "";
            author = "";
            year = 0;
        }
    }

    @Override
    public void characters(
            char[] ch,
            int start,
            int length
    ) throws SAXException {

        String text = new String(ch, start, length).trim();

        if (text.isEmpty()) {
            return;
        }

        if (currentElement.equals("title")) {
            title += text;
        }

        if (currentElement.equals("author")) {
            author += text;
        }

        if (currentElement.equals("year")) {
            year = Integer.parseInt(text);
        }
    }

    @Override
    public void endElement(
            String uri,
            String localName,
            String qName
    ) throws SAXException {

        if (qName.equals("book")) {

            if (year >= filterYear) {
                System.out.println("Название: " + title);
                System.out.println("Автор: " + author);
                System.out.println("Год: " + year);
                System.out.println("----------------------");
            }
        }

        currentElement = "";
    }
}
