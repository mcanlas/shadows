package com.htmlism.shadows.plato

import java.io.PrintWriter

import cats.effect._

object PrintWriterResource {
  def apply[F[_]](dest: String)(implicit F: Sync[F]): Resource[F, ResourceFileAlg[F]] =
    Resource.make {
      F.delay {
        new ResourceFileAlg[F] {
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
        } : ResourceFileAlg[F]
      }
    } (_.close)
}

trait ResourceFileAlg[F[_]] {
  def println(s: String): F[Unit]

  def close: F[Unit]
}
