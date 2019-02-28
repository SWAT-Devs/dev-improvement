package dev.improvement

import spock.lang.*

/**
 * @see https://www.reddit.com/r/dailyprogrammer/comments/aqwvxo/20190215_challenge_375_hard_graph_of_thrones/
 * @see https://www.youtube.com/watch?v=qEKNFOaGQcc
 */
class GraphOfThronesTest extends Specification {

  def testTheParser() {
    when:
    def g = parse(new StringReader('A ++ B'))
    then:
    g == new Graph().friends('A', 'B')
  }

  Graph parse(Reader r){
    def g = new Graph()
    println 'new graph'
    return g
  }

  @groovy.transform.EqualsAndHashCode
  static class Graph {
    final Map<String, Set<String>> friends = new TreeMap<>()
    final Map<String, Set<String>> enemies = new TreeMap<>()

    private Set<String> get(Map<String, Set<String>> m, String name){
      m.computeIfAbsent(name) { new TreeSet<>() }
    }
    
    Graph friends(String a, String b){
      get(friends, a) << b
      get(friends, b) << a
      return this
    }

    Graph enemies(String a, String b){
      get(enemies, a) << b
      get(enemies, b) << a
      return this
    }

    @Override
    String toString() { "Graph [friends: $friends, enemies: $enemies]" }
  }
}
