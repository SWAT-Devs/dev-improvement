package dev.improvement

import spock.lang.*

/**
 * @see https://www.reddit.com/r/dailyprogrammer/comments/aqwvxo/20190215_challenge_375_hard_graph_of_thrones/
 * @see https://www.youtube.com/watch?v=qEKNFOaGQcc
 */
class GraphOfThronesTest extends Specification {
  def 'Ballanced: A ++ B, B ++ C, A ++ C'() {
    when:
      def g = new Graph().friends('A', 'B').friends('B', 'C').friends('A', 'C')
    then:
      isBallanced(g)
  }

  def 'Ballanced: A -- B, B -- C, A ++ C'(){
    when:
      def g = new Graph().enemies('A', 'B').enemies('B', 'C').friends('A', 'C')
    then:
      isBallanced(g)
  }

  def 'Unballanced: A -- B, B ++ C, A ++ C'(){
    when:
      def g = new Graph().enemies('A', 'B').friends('B', 'C').friends('A', 'C')
    then:
      !isBallanced(g)
  }

  def 'Unballanced: A -- B, B -- C, A -- C'() {
    when:
      def g = new Graph().enemies('A', 'B').enemies('B', 'C').enemies('A', 'C')
    then:
      !isBallanced(g)
  }

  def 'Unbalanced: A ++ B, B -- C, A -- C, B -- D, C -- D, A -- D'() {
    given:
      def (a, b, c, d) = ['A', 'B', 'C', 'D']
    when:
      def g = new Graph().friends(a, b).enemies(b, c).enemies(a, c).enemies(b, d).enemies(c, d).enemies(a, d)
    then:
      !isBallanced(g)
  }

  def 'Justice League'() {
    when:
      def g = getClass().getResource('/JusticeLeague.txt').withReader { parse it }
    then:
      isBallanced(g)
  }

  def 'Game of Thrones'(){
    when:
      def g = getClass().getResource('/GoT.txt').withReader { parse it }
    then:
      !isBallanced(g)
  }

  boolean isBallanced(g) {
    if(g.friends.isEmpty())
      return false
    
    def enemiesVertices = g.enemies.keySet()
    for(String vertice : enemiesVertices) {
      for(String enemy : g.enemies.get(vertice)) {
        for(String friendOfEnemy : g.friends.get(enemy)) {
          if(g.friends.containsKey(vertice) && g.friends.get(vertice).contains(friendOfEnemy))
          return false
        }
      }
    }
    return true
  }

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
