package com.yubico.webauthn.test

import java.io.InputStream
import java.io.BufferedReader
import java.io.InputStreamReader
import java.security.cert.X509Certificate

import com.yubico.u2f.data.messages.key.util.CertificateParser
import org.bouncycastle.cert.X509CertificateHolder
import org.bouncycastle.openssl.PEMParser

import scala.util.Try


object Util {

  def importCertFromPem(certPem: InputStream): X509Certificate =
    CertificateParser.parseDer(
      new PEMParser(new BufferedReader(new InputStreamReader(certPem)))
        .readObject()
        .asInstanceOf[X509CertificateHolder]
        .getEncoded
    )

  type Stepish[A] = { def validate(): Unit; def next(): A }
  case class StepWithUtilities[A](a: Stepish[A]) {
    def validations: Try[Unit] = Try(a.validate())
    def tryNext: Try[A] = Try(a.next())
  }
  implicit def toStepWithUtilities[A](a: Stepish[A]): StepWithUtilities[A] = StepWithUtilities(a)

}
