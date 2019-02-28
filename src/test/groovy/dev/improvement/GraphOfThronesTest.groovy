package dev.improvement

import spock.lang.*

/**
 * @see https://www.reddit.com/r/dailyprogrammer/comments/aqwvxo/20190215_challenge_375_hard_graph_of_thrones/
 * @see https://www.youtube.com/watch?v=qEKNFOaGQcc
 */
class GraphOfThronesTest extends Specification {

  def testTheParser() {
    when:
      def g = parse 'A ++ B'
    then:
      g == new Graph().friends('A', 'B')
    when:
      g = parse 'A -- B'
    then:
      g == new Graph().enemies('A', 'B')
    when:
      g = parse """A ++ B
A -- C
B ++ C"""
    then:
      g == new Graph().friends('A', 'B').enemies('A', 'C').friends('B', 'C')
  }

  Graph parse(String value) { parse(new StringReader(value)) }
  Graph parse(Reader r){
    def g = new Graph()
    def pattern = ~/(?<n1>[A-Za-z\s]+)\s*(?<op>(\+\+)|(\-\-))\s*(?<n2>[A-Za-z\s]+)/
    r.eachLine { line ->
      def m = pattern.matcher(line)
      if(!m.find()) return
      def n1 = m.group('n1').trim()
      def n2 = m.group('n2').trim()
      if(m.group('op') == '++') g.friends(n1, n2)
      else g.enemies(n1, n2)
    }
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
