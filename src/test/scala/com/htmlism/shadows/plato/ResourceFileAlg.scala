package com.htmlism.shadows.plato

import java.io.PrintWriter

import cats.effect._

object PrintWriterResource {
  def apply[F[_] : Sync](dest: String): Resource[F, ResourceFileAlg[F]] =
    Resource.make {
      Sync[F].delay {
        new ResourceFileAlgSync[F](dest) : ResourceFileAlg[F]
      }
    } (_.close)

  private class ResourceFileAlgSync[F[_]](dest: String)(implicit F: Sync[F]) extends ResourceFileAlg[F] {
    private[this] lazy val printer =
      new PrintWriter("src/main/resources/" + dest)

    def println(s: String): F[Unit] =
      F.delay {
        printer.println(s)
      }

    def close: F[Unit] =
      F.delay {
        printer.close()
      }
  }
}

trait ResourceFileAlg[F[_]] {
  def println(s: String): F[Unit]

  def close: F[Unit]
}
