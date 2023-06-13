package com.htmlism.shadows.plato

import java.io.PrintWriter

import cats.effect._

object PrintWriterResource:
  def apply[F[_]](dest: String)(implicit F: Sync[F]): Resource[F, ResourceFileAlg[F]] =
    Resource
      .fromAutoCloseable:
        F.delay:
          new PrintWriter("src/main/resources/" + dest)
      .map(ResourceFileAlg[F])

trait ResourceFileAlg[F[_]]:
  def println(s: String): F[Unit]

object ResourceFileAlg:
  def apply[F[_]](pw: PrintWriter)(implicit F: Sync[F]): ResourceFileAlg[F] =
    new ResourceFileAlg[F]:
      def println(s: String): F[Unit] =
        F.delay:
          pw.println(s)
