package com.htmlism.shadows.plato

import java.io.PrintWriter

import cats.effect._

object SafePrintWriter {
  def createResource[F[_]](s: String)(implicit F: Sync[F]): Resource[F, PrintWriter] =
    Resource.fromAutoCloseable {
      F.delay {
        new java.io.PrintWriter("src/main/resources/" + s)
      }
    }
}

trait FileAlg[F[_]] {
  def openResource: F[PrintWriter]
}
