package graphlib;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ GraphEdgeDefinitionTest.class, GraphParallelTest.class, GraphPathFindTest.class,
		GraphVertexDefinitionTest.class, TraverseGraphTest.class })
public class AllTests {

}
