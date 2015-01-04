class BankAccount {
  private var bal: Int = 0
  def balance: Int = bal
  def deposit (amt: Int) = { bal += amt }
  def withdraw (amt: Int) = { bal -= amt }
}

val checking = new BankAccount

checking.deposit {40}
assert(checking.balance == 40)

