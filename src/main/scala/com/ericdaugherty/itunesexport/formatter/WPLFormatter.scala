package com.ericdaugherty.itunesexport.formatter

import java.io.{File, PrintWriter}
import java.text.MessageFormat

import com.ericdaugherty.itunesexport.parser.{Track, Playlist}

/**
 * Formats a given playlist as an WPL playlist used by Windows Media Player.
 *
 * @author Eric Daugherty
 */
class WPLFormatter(settings: FormatterSettings) extends Formatter(settings) {

  def writePlaylist(playlist: Playlist) {
    // Write out each track using a PrintWriter
    withPrintWriter(new File(settings.outputDirectory, parseFileName(playlist) + ".wpl"), settings) { writer =>
      writer.println("<?wpl version=\"1.0\"?>")
      writer.println(<smil>
  <head>
    <author />
    <title>{playlist.name}</title>
  </head>
  <body>
    <seq>
    {for(track <- filterTracks(playlist.tracks,settings)) yield <media src={copyFiles(track, playlist)}></media>}
    </seq>
  </body>
</smil>)
    }
  }
}
