package dev.improvement

import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

import spock.lang.Specification


/**
 * @see https://www.reddit.com/r/dailyprogrammer/comments/aqwvxo/20190215_challenge_375_hard_graph_of_thrones/
 * @see https://www.youtube.com/watch?v=qEKNFOaGQcc
 */
class GraphOfThronesTest extends Specification {
  def 'Balanced: A ++ B, B ++ C, A ++ C'() {
    when:
      def g = new Graph().friends('A', 'B').friends('B', 'C').friends('A', 'C')
    then:
      isBalanced(g)
  }

  def 'Balanced: A -- B, B -- C, A ++ C'(){
    when:
      def g = new Graph().enemies('A', 'B').enemies('B', 'C').friends('A', 'C')
    then:
      isBalanced(g)
  }

  def 'Unbalanced: A -- B, B ++ C, A ++ C'(){
    when:
      def g = new Graph().enemies('A', 'B').friends('B', 'C').friends('A', 'C')
    then:
      !isBalanced(g)
  }

  def 'Unbalanced: A -- B, B -- C, A -- C'() {
    when:
      def g = new Graph().enemies('A', 'B').enemies('B', 'C').enemies('A', 'C')
    then:
      !isBalanced(g)
  }

  def 'Unbalanced: A ++ B, B -- C, A -- C, B -- D, C -- D, A -- D'() {
    given:
      def (a, b, c, d) = ['A', 'B', 'C', 'D']
    when:
      def g = new Graph().friends(a, b).enemies(b, c).enemies(a, c).enemies(b, d).enemies(c, d).enemies(a, d)
    then:
      !isBalanced(g)
  }
  
  def 'Justice League'() {
    when:
      def g = getClass().getResource('/JusticeLeague.txt').withReader { parse it }
    then:
      isBalanced(g)
  }

  def 'Game of Thrones'(){
    when:
      def g = getClass().getResource('/GoT.txt').withReader { parse it }
    then:
      !isBalanced(g)
  }

  @CompileStatic
  static boolean isBalanced(Graph g) {
    if(!g.friends)
      return false
    if(!g.enemies)
      return true
    def first = g.friends.keySet().first()
    def group1 = g.friends[first] + first
    def enemies = g.enemies[first]
    if(!enemies)
      return false
    def firstEnemy = enemies.first()
    def group2 = (g.friends[firstEnemy] ?: Collections.<String>emptySet()) + firstEnemy
    if(intersects(group1, group2))
      return false
    return g.friends.keySet() + g.enemies.keySet() == group1 + group2
  }

  @CompileStatic
  static boolean intersects(Set s1, Set s2) {
    if(s1.size() > s2.size()) {
      def x = s1
      s1 = s2
      s2 = x
    }
    return s1.any { it in s2 }
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

  @CompileStatic
  Graph parse(String value) { parse(new StringReader(value)) }

  @CompileStatic
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

  @EqualsAndHashCode
  @CompileStatic
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
