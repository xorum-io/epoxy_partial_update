package io.xorum.epoxypartialupdate.data

import io.xorum.epoxypartialupdate.model.Phrase

interface DataSource {

    fun getPhrases(): List<Phrase>
}

/**
 * Source: https://en.wikibooks.org/wiki/French/Vocabulary/Animals
 */
class MockDataSource : DataSource {

    override fun getPhrases() = listOf(
        Phrase("A cat", "Un chat"),
        Phrase("A dog", "Un chien"),
        Phrase("A Guinea pig", "Un cochon d'Inde"),
        Phrase("A rabbit", "Un lapin"),
        Phrase("A mouse", "Une souris"),
        Phrase("A parrot", "Un perroquet"),
        Phrase("A hamster", "Un hamster"),
        Phrase("A horse", "Un cheval"),
        Phrase("A snake", "Un serpent"),
        Phrase("A cow", "Une vache"),
        Phrase("A bull", "Un taureau"),
        Phrase("An ox", "Un bœuf"),
        Phrase("A calf", "Un veau"),
        Phrase("A sheep", "Un mouton"),
        Phrase("A ewe", "Une brebis"),
        Phrase("A lamb", "Un agneau"),
        Phrase("A goat", "Une chèvre"),
        Phrase("A pig", "Un cochon"),
        Phrase("A sow", "Une truie"),
        Phrase("A mare", "Une jument")
    )
}