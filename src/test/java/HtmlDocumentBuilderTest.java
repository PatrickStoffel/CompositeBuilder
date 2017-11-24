import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;

@RunWith(OleasterRunner.class)
public class HtmlDocumentBuilderTest {{

  describe("Single node", () -> {
    it("should be possible to build a single node with doctype", () -> {
     HtmlDocumentBuilder builder = HtmlDocumentBuilder.create("html5");
     AttributedCompositeNode node = AttributedCompositeNode.create("node");
     builder.setRoot(node);

     String html = builder.build();
     expect(html).toEqual("html5\n<node></node>");
    });

    it("should be possible to build an empty document", () -> {
      HtmlDocumentBuilder builder = HtmlDocumentBuilder.create(null);

      String html = builder.build();
      expect(html).toEqual("");
    });

    it("should be possible to build a document with just a doctype", () -> {
      HtmlDocumentBuilder builder = HtmlDocumentBuilder.create("html5");

      String html = builder.build();
      expect(html).toEqual("html5\n");
    });

    it("should be possible to build a document with just one node", () -> {
      HtmlDocumentBuilder builder = HtmlDocumentBuilder.create("");

      AttributedCompositeNode node = AttributedCompositeNode.create("node");
      builder.setRoot(node);

      String html = builder.build();
      expect(html).toEqual("<node></node>");
    });

    it("should be possible to build a single node with single attributes", () -> {
      HtmlDocumentBuilder builder = HtmlDocumentBuilder.create("");
      AttributedCompositeNode node = AttributedCompositeNode.create("node");
      node.setAttribute("key", "value");
      node.setAttribute("key1", "value1");
      builder.setRoot(node);

      String html = builder.build();
      expect(html).toEqual("<node key='value' key1='value1'></node>");
    });
  });

  describe("Composition", () -> {
    it("should be possible to created nested html", () -> {
      HtmlDocumentBuilder builder = HtmlDocumentBuilder.create("html5");
      AttributedCompositeNode root = AttributedCompositeNode.create("html");

      builder.setRoot(root);
      root.addChild(HtmlNode.createA("about:blank"));
      String html = builder.build();

      expect(html).toEqual("html5\n<html><a href='about:blank'></a></html>");
    });
  });
}}