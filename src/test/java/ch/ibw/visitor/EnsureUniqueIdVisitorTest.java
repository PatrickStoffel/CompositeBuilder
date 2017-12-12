package ch.ibw.visitor;

import ch.ibw.HtmlNode;
import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;

import static com.mscharhag.oleaster.matcher.Matchers.expect;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.describe;
import static com.mscharhag.oleaster.runner.StaticRunnerSupport.it;

@RunWith(OleasterRunner.class)
public class EnsureUniqueIdVisitorTest {{

  describe("acceptAll", () -> {
    it("should remove all except first duplicate id", () -> {
      CompositeVisitor visitor = new EnsureUniqueIdVisitor();

      HtmlNode id1 = HtmlNode.create("name1");
      id1.setAttribute("id", "1");
      HtmlNode id1AsWell = HtmlNode.create("name2");
      id1AsWell.setAttribute("id", "1");

      HtmlNode node = (HtmlNode) id1
              .addChild(id1AsWell);

      visitor.acceptAll(node);

      expect(id1.attributes.get("id")).toEqual("1");
      expect(id1AsWell.attributes.get("id")).toBeNotNull();
    });
  });
}}
